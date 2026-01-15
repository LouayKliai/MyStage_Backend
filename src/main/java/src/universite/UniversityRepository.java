package src.universite;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UniversityRepository extends JpaRepository<University, UUID> {

    boolean existsByNom(String nom);

    // Charger une université avec ses étudiants (évite LazyInitializationException)
    @Query("""
           SELECT u FROM University u
           LEFT JOIN FETCH u.listEtudiant
           WHERE u.id = :id
           """)
    Optional<University> findByIdWithStudents(UUID id);
}
