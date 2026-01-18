package src.Offre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import src.DTO.Offer.OfferCreateDTO;
import src.DTO.Offer.OfferResponseDTO;
import src.DTO.Offer.OfferUpdateDTO;
import src.RH.RH;
import src.RH.RhRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
public class OffreServiceImpl implements OffreService {	
	    private final OffreRepository offreRepo;
	    private final RhRepository rhRepo; 

	    public OffreServiceImpl(OffreRepository offreRepo,
	    						RhRepository rhRepo) {
	        this.offreRepo = offreRepo;
	        this.rhRepo=rhRepo;
	    }
/*
	    @Override
	    public OfferResponseDTO createOffer(RH rh, OfferCreateDTO dto) {

	        Offre offre = new Offre();
	        offre.setTitle(dto.getTitle());
	        offre.setDescription(dto.getDescription());
	        offre.setDuree(dto.getDuree());
	        offre.setType(dto.getType());
	        offre.setQualification(dto.getQualification());
	        offre.setSociete(rh.getSociete());

	        return mapToDTO(offreRepo.save(offre));
	    }
*/
	    
	    @Override
	    public OfferResponseDTO createOffer(UUID rhId, OfferCreateDTO dto) {

	        RH rh = rhRepo.findById(rhId)
	                .orElseThrow(() -> new EntityNotFoundException("RH introuvable"));

	        Offre offre = new Offre();
	        offre.setTitle(dto.getTitle());
	        offre.setDescription(dto.getDescription());
	        offre.setDuree(dto.getDuree());
	        offre.setType(dto.getType());
	        offre.setQualification(dto.getQualification());
	        offre.setSociete(rh.getSociete());

	        Offre saved = offreRepo.save(offre);
	        return mapToDTO(saved);
	    }

	    @Override
	    public List<OfferResponseDTO> getOffersByRH(RH rh) {

	        return offreRepo.findBySociete(rh.getSociete())
	                .stream()
	                .map(this::mapToDTO)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<OfferResponseDTO> getOffersBySociete(String nomSociete) {

	        return offreRepo.findBySociete_Nom(nomSociete)
	                .stream()
	                .map(this::mapToDTO)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<OfferResponseDTO> getOffers() {

	        return offreRepo.findAll()
	                .stream()
	                .map(this::mapToDTO)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public OfferResponseDTO updateOffer(int offerId, OfferUpdateDTO dto, RH rh) {

	        Offre offre = offreRepo.findById(offerId)
	                .orElseThrow(() -> new EntityNotFoundException("Offre non trouvée"));

	        if (!offre.getSociete().getId().equals(rh.getSociete().getId())) {
	            throw new RuntimeException("Accès refusé");
	        }

	        if (dto.getTitle() != null) offre.setTitle(dto.getTitle());
	        if (dto.getDescription() != null) offre.setDescription(dto.getDescription());
	        if (dto.getDuree() > 0) offre.setDuree(dto.getDuree());
	        if (dto.getType() != null) offre.setType(dto.getType());
	        if (dto.getQualification() != null) offre.setQualification(dto.getQualification());

	        return mapToDTO(offreRepo.save(offre));
	    }

	    @Override
	    public void deleteOffer(int offerId) {

	        Offre offre = offreRepo.findById(offerId)
	                .orElseThrow(() -> new EntityNotFoundException("Offre non trouvée"));

	        offreRepo.delete(offre);
	    }

	    private OfferResponseDTO mapToDTO(Offre offre) {

	        return new OfferResponseDTO(
	                offre.getId(),
	                offre.getTitle(),
	                offre.getDescription(),
	                offre.getDuree(),
	                offre.getType(),
	                offre.getQualification(),
	                offre.getSociete().getNom(),
	                offre.getCandidatures() == null ? 0 : offre.getCandidatures().size()
	        );
	    }
}
	
