package src.universite;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UniversityService {
	University createUniversity(University university);

    List<University> getAllUniversities();

    Optional<University> getUniversityById(UUID id);

    Optional<University> getUniversityWithStudents(UUID id);

    void deleteUniversity(UUID id);

}
