package src.DTO.Candidature;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidatureCreateDTO {
    private int offreId;   // ID de l'offre à laquelle l'étudiant postule
    private String motivation; // facultatif : texte de motivation
}