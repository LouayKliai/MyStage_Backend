package src.universite;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<University> createUniversity(
            @RequestBody University university) {

        University saved = universityService.createUniversity(university);
        return ResponseEntity.ok(saved);
    }

    // GET ALL
    @GetMapping
    public List<University> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable UUID id) {

        return universityService.getUniversityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ GET université + étudiants
    @GetMapping("/{id}/students")
    public ResponseEntity<University> getUniversityWithStudents(
            @PathVariable UUID id) {

        return universityService.getUniversityWithStudents(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ❌ DELETE avec règle métier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable UUID id) {

        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }
}
