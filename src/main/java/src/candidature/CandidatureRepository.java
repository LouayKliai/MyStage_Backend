package src.candidature;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
	Optional<Candidature> findById(Long id);
    boolean existsByStudentIdAndOffreId(UUID studentId, int offreId);

    List<Candidature> findByOffreId(int offreId);
    List<Candidature> findByStudentId(UUID studentId);

}
