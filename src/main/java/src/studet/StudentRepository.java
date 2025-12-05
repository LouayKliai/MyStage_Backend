package src.studet;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student,UUID>{

	Optional<Student> getStudentById(UUID id);	

}
