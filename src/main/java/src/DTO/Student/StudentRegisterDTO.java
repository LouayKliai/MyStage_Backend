package src.DTO.Student;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegisterDTO {
		private String fullname;
	    private String email;
	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	    private String password;

}
