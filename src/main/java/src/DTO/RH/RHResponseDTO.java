package src.DTO.RH;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class RHResponseDTO {

	private UUID id;
    private String fullname;
    private String email;
    private String nomSociete;
}
