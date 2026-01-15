package src.Offre;

import java.util.UUID;

import org.springframework.stereotype.Service;
import src.RH.RH;
import src.RH.RhRepository;

@Service
public class OffreServiceImpl implements OffreService {

    private final OffreRepository offreRepo;
    private final RhRepository rhRepo;

    public OffreServiceImpl(OffreRepository offreRepo, RhRepository rhRepo) {
        this.offreRepo = offreRepo;
        this.rhRepo = rhRepo;
    }

    @Override
    public Offre createOffer(UUID rhId, Offre offre) {

        RH rh = rhRepo.findById(rhId)
        		.orElseThrow(() -> new RuntimeException("RH introuvable"));

        offre.setSociete(rh.getSociete());

        return offreRepo.save(offre);
    }

}
