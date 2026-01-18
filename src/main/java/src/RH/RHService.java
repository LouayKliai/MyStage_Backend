package src.RH;

import src.DTO.RH.RHLoginDTO;
import src.DTO.RH.RHRegisterDTO;
import src.DTO.RH.RHResponseDTO;

public interface RHService {
		RH register(RHRegisterDTO dto);
	    RHResponseDTO login(RHLoginDTO dto);

}
