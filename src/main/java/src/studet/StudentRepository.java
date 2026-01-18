package src.studet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import src.universite.University;
import src.utils.Specialite;

public interface StudentRepository  extends JpaRepository<Student,UUID>{	
	 @EntityGraph(attributePaths = {"university", "skills"})
	 Optional<Student> findById(UUID id);
	
	@EntityGraph(attributePaths = {"skills", "university"})
	Optional<Student> findByEmail(String email);
	List<Student> findBySpecialite(Specialite specialite);
	List<Student> findByUniversityId(UUID university_id);

	

}
