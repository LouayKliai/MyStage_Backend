package src.candidature;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidatures")

public class CandidatureController {
	

	    private final CandidatureService candidatureService;

	    public CandidatureController(CandidatureService candidatureService) {
	        this.candidatureService = candidatureService;
	    }

	    @PostMapping("/apply")
	    public ResponseEntity<Candidature> apply(
	            @RequestParam UUID studentId,
	            @RequestParam int offreId) {

	        return ResponseEntity.ok(
	                candidatureService.postuler(studentId, offreId)
	        );
	    }
	

}
