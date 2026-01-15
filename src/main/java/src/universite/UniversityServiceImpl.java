package src.universite;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepo;

    public UniversityServiceImpl(UniversityRepository universityRepo) {
        this.universityRepo = universityRepo;
    }

    @Override
    public University createUniversity(University university) {

        if (university.getNom() == null || university.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'université est obligatoire");
        }

        if (universityRepo.existsByNom(university.getNom())) {
            throw new IllegalArgumentException("Université déjà existante");
        }

        return universityRepo.save(university);
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepo.findAll();
    }

    @Override
    public Optional<University> getUniversityById(UUID id) {
        return universityRepo.findById(id);
    }

    @Override
    public Optional<University> getUniversityWithStudents(UUID id) {
        return universityRepo.findByIdWithStudents(id);
    }

    /**
     * RÈGLE MÉTIER DELETE :
     * - si l'université contient des étudiants → REFUS
     * - sinon → suppression autorisée
     */
    @Override
    public void deleteUniversity(UUID id) {

        University university = universityRepo.findByIdWithStudents(id)
                .orElseThrow(() -> new EntityNotFoundException("Université introuvable"));

        if (!university.getListEtudiant().isEmpty()) {
            throw new IllegalStateException(
                    "Suppression impossible : l'université contient des étudiants");
        }

        universityRepo.delete(university);
    }
}
