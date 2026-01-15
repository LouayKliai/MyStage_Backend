package src.candidature;

import java.util.UUID;

import org.springframework.stereotype.Service;

import src.Offre.Offre;
import src.Offre.OffreRepository;
import src.RH.RH;
import src.RH.RhRepository;
import src.studet.Student;
import src.studet.StudentRepository;
import src.utils.StatutCandidature;
@Service
public class CandidatureServiceImpl implements CandidatureService{
	 private final CandidatureRepository candidatureRepo;
	    private final StudentRepository studentRepo;
	    private final OffreRepository offreRepo;
	    private final RhRepository rhRepo;

	    public CandidatureServiceImpl(CandidatureRepository candidatureRepo,
	                              StudentRepository studentRepo,
	                              OffreRepository offreRepo,
	                              RhRepository rhRepo) {
	        this.candidatureRepo = candidatureRepo;
	        this.studentRepo = studentRepo;
	        this.offreRepo = offreRepo;
	        this.rhRepo=rhRepo;
	    }

		@Override
		public Candidature postuler(UUID studentId, int offreId) {

	        if (candidatureRepo.existsByStudentIdAndOffreId(studentId, offreId)) {
	            throw new IllegalStateException("Déjà postulé à cette offre");
	        }

	        Student student = studentRepo.findById(studentId)
	                .orElseThrow(() -> new RuntimeException("Student introuvable"));

	        Offre offre = offreRepo.findById(offreId)
	                .orElseThrow(() -> new RuntimeException("Offre introuvable"));

	        Candidature candidature = new Candidature();
	        candidature.setStudent(student);
	        candidature.setOffre(offre);

	        return candidatureRepo.save(candidature);
	    
		}

		@Override
		public Candidature accepter(Long candidatureId, UUID rhId) {
			Candidature candidature = candidatureRepo.findById(candidatureId)
	                .orElseThrow(() -> new RuntimeException("Candidature introuvable"));

	        RH rh = rhRepo.findById(rhId)
	                .orElseThrow(() -> new RuntimeException("RH introuvable"));

	        // 🔐 Sécurité métier
	        if (!candidature.getOffre().getSociete().getId()
	                .equals(rh.getSociete().getId())) {
	            throw new SecurityException("Accès interdit");
	        }

	        candidature.setStatut(StatutCandidature.ACCEPTEE);
	        return candidature;
		}

		@Override
		public Candidature refuser(Long candidatureId, UUID rhId) {
			 Candidature candidature = candidatureRepo.findById(candidatureId)
		                .orElseThrow(() -> new RuntimeException("Candidature introuvable"));

		        RH rh = rhRepo.findById(rhId)
		                .orElseThrow(() -> new RuntimeException("RH introuvable"));

		        if (!candidature.getOffre().getSociete().getId()
		                .equals(rh.getSociete().getId())) {
		            throw new SecurityException("Accès interdit");
		        }

		        candidature.setStatut(StatutCandidature.REFUSEE);
		        return candidature;
		    }
		}



