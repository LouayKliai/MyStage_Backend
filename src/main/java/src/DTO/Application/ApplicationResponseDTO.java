package src.DTO.Application;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import src.utils.StatutCandidature;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDTO {
    private Long id;
    private String offreTitle;
    private String rhName;
    private StatutCandidature status;
}
