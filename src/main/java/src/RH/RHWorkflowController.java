package src.RH;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import src.candidature.Candidature;
import src.candidature.CandidatureService;


@RestController
@RequestMapping("/rh/candidatures")
public class RHWorkflowController {

    private final CandidatureService candidatureService;

    public RHWorkflowController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }

    // ✅ accepter
    @PutMapping("/{id}/accept")
    public ResponseEntity<Candidature> accepter(
            @PathVariable Long id,
            @RequestParam UUID rhId) {

        return ResponseEntity.ok(
                candidatureService.accepter(id, rhId)
        );
    }

    // ❌ refuser
    @PutMapping("/{id}/refuse")
    public ResponseEntity<Candidature> refuser(
            @PathVariable Long id,
            @RequestParam UUID rhId) {

        return ResponseEntity.ok(
                candidatureService.refuser(id, rhId)
        );
    }
}
