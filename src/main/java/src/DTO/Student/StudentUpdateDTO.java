package src.DTO.Student;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.utils.Specialite;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor

public class StudentUpdateDTO {


    private String fullname;
    private String email;
    private String niveau;
    private Specialite specialite;
    private String bio;
    private List<String> skills;

}
