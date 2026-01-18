package src.candidature;

import src.DTO.Candidature.CandidatureCreateDTO;
import src.DTO.Candidature.CandidatureResponseDTO;
import src.studet.Student;

public interface CandidatureStudentService {
	CandidatureResponseDTO postuler(Student student, CandidatureCreateDTO dto);


}
