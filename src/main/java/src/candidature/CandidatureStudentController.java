package src.candidature;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import src.studet.Student;
import src.DTO.Candidature.CandidatureCreateDTO;
import src.DTO.Candidature.CandidatureResponseDTO;

@RestController
@RequestMapping("/students/candidatures")
public class CandidatureStudentController {

    private final CandidatureStudentService candidatureService;

    public CandidatureStudentController(CandidatureStudentService candidatureService) {
        this.candidatureService = candidatureService;
    }

    @PostMapping("/postuler")
    public ResponseEntity<CandidatureResponseDTO> postuler(
            @AuthenticationPrincipal Student student,
            @RequestBody CandidatureCreateDTO dto) {

        return ResponseEntity.ok(
                candidatureService.postuler(student, dto)
        );
    }
}
