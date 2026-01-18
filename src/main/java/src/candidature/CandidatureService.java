package src.candidature;


import src.DTO.Application.ApplicationResponseDTO;
import src.RH.RH;

public interface CandidatureService {
	
	
	ApplicationResponseDTO accepter(Long candidatureId, RH rh);
    ApplicationResponseDTO refuser(Long candidatureId, RH rh);

}
