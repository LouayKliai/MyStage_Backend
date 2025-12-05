package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import src.universite.University;
import src.utils.Specialite;

public interface StudentRepository  extends JpaRepository<Student,UUID>{	
	Optional<Student> findStudentByEmail(String email);
	List<Student> findBySpecialite(Specialite specialite);
	List<Student> findByUniversityId(UUID university_id);

	

}
