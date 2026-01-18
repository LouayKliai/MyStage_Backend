package src.candidature;



import org.springframework.stereotype.Service;


import jakarta.persistence.EntityNotFoundException;
import src.DTO.Application.ApplicationResponseDTO;
import src.Offre.Offre;
import src.Offre.OffreRepository;
import src.RH.RH;
import src.utils.StatutCandidature;
@Service
public class CandidatureServiceImpl implements CandidatureService{
	   private final CandidatureRepository candidatureRepo;
	    private final OffreRepository offreRepo;

	    public CandidatureServiceImpl(CandidatureRepository candidatureRepo, OffreRepository offreRepo) {
	        this.candidatureRepo = candidatureRepo;
	        this.offreRepo = offreRepo;
	    }

	    @Override
	    public ApplicationResponseDTO accepter(Long candidatureId, RH rh) {
	        return changerStatus(candidatureId, rh, StatutCandidature.ACCEPTEE);
	    }

	    @Override
	    public ApplicationResponseDTO refuser(Long candidatureId, RH rh) {
	        return changerStatus(candidatureId, rh, StatutCandidature.REFUSEE);
	    }

	    private ApplicationResponseDTO changerStatus(Long candidatureId, RH rh, StatutCandidature newStatus) {
	        var candidature = candidatureRepo.findById(candidatureId)
	                .orElseThrow(() -> new EntityNotFoundException("Candidature non trouvée"));

	        Offre offre = candidature.getOffre();

	        // Vérifie que la candidature appartient à une offre du RH connecté
	        if (!offre.getSociete().getId().equals(rh.getSociete().getId())) {
	            throw new RuntimeException("Vous n'avez pas le droit de modifier cette candidature");
	        }

	        candidature.setStatut(newStatus);
	        var saved = candidatureRepo.save(candidature);

	        return new ApplicationResponseDTO(
	                saved.getId(),
	                saved.getOffre().getTitle(),
	                rh.getFullname(),
	                saved.getStatut()
	        );
	    }

		
}