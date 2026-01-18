package src.DTO.Candidature;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.utils.StatutCandidature;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidatureResponseDTO {
    private Long id;
    private String offreTitle;
    private String rhName;
    private StatutCandidature status;
    private String motivation;
}