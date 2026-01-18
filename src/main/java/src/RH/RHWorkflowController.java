package src.RH;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import src.DTO.Application.ApplicationResponseDTO;
import src.candidature.Candidature;
import src.candidature.CandidatureService;
@RestController
@RequestMapping("/rh/candidatures")
public class RHWorkflowController {

    private final CandidatureService candidatureService;

    public RHWorkflowController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }

    // Accepter candidature
    @PutMapping("/{id}/accept")
    public ResponseEntity<ApplicationResponseDTO> accepter(
            @PathVariable Long id,
            @AuthenticationPrincipal RH rh) {
        return ResponseEntity.ok(
                candidatureService.accepter(id, rh)
        );
    }

    // Refuser candidature
    @PutMapping("/{id}/refuse")
    public ResponseEntity<ApplicationResponseDTO> refuser(
            @PathVariable Long id,
            @AuthenticationPrincipal RH rh) {
        return ResponseEntity.ok(
                candidatureService.refuser(id, rh)
        );
    }
}
