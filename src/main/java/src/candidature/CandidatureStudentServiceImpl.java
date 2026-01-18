package src.candidature;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import src.DTO.Candidature.CandidatureResponseDTO;
import src.DTO.Candidature.CandidatureCreateDTO;
import src.Offre.Offre;
import src.Offre.OffreRepository;
import src.RH.RH;
import src.studet.Student;
import src.utils.StatutCandidature;

@Service
@Transactional
public class CandidatureStudentServiceImpl implements CandidatureStudentService {

    private final CandidatureRepository candidatureRepo;
    private final OffreRepository offreRepo;

    public CandidatureStudentServiceImpl(CandidatureRepository candidatureRepo, OffreRepository offreRepo) {
        this.candidatureRepo = candidatureRepo;
        this.offreRepo = offreRepo;
    }

    @Override   
    public CandidatureResponseDTO postuler(Student student, CandidatureCreateDTO dto) {

    // 1️⃣ Trouver l'offre
    Offre offre = offreRepo.findById(dto.getOffreId())
            .orElseThrow(() -> new EntityNotFoundException("Offre non trouvée"));

    // 2️⃣ Vérifier si l'étudiant a déjà postulé
    candidatureRepo.findByStudentAndOffre(student, offre)
            .ifPresent(c -> { throw new RuntimeException("Vous avez déjà postulé à cette offre"); });
    // 3️⃣ Créer la candidature
    var candidature = new Candidature();
    candidature.setStudent(student);
    candidature.setOffre(offre);
    candidature.setMotivation(dto.getMotivation());
    candidature.setStatut(StatutCandidature.EN_ATTENTE);

    var saved = candidatureRepo.save(candidature);

    // 4️⃣ Récupérer un RH responsable
    RH rh = offre.getSociete().getListeRh().get(0);

    // 5️⃣ Retourner le DTO
    return new CandidatureResponseDTO(
            saved.getId(),
            offre.getTitle(),
            rh.getFullname(),
            saved.getStatut(),
            saved.getMotivation()
    );
}

}
