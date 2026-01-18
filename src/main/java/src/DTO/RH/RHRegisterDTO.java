package src.DTO.RH;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class RHRegisterDTO {

	private String fullname;
    private String email;
    private String password;
    private String societeId;

}
