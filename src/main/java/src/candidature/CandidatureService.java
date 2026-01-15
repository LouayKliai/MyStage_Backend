package src.candidature;

import java.util.UUID;

public interface CandidatureService {
	
	Candidature postuler(UUID studentId, int offreId);
	Candidature accepter(Long candidatureId, UUID rhId);
	Candidature refuser(Long candidatureId, UUID rhId);
}
