package src.DTO.Student;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.utils.Specialite;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class StudentProfileDTO {

	private String fullName;
    private String email;
    private Specialite specialite;
    private List<String> skills;
    private UUID universityId;
    private String bio;

}
