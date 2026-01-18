package src.DTO.Application;

import src.utils.StatutCandidature;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ApplicationUpdateDTO {
    private StatutCandidature status; // ACCEPTE / REFUSE
}
