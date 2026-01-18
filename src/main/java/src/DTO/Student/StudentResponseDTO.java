package src.DTO.Student;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.utils.Specialite;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

	private UUID id;
	private String fullname;
	private String email;
	private List<String> skills;
	private String niveau;
	private Specialite specialite;
	private String bio;
	
	

}
