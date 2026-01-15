package src.Offre;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offres")
public class OffreController {

    private final OffreService offreService;

    public OffreController(OffreService offreService) {
        this.offreService = offreService;
    }

    @PostMapping("/create/{rhId}")
    public ResponseEntity<Offre> create(@PathVariable UUID rhId, @RequestBody Offre offre) {
        Offre saved = offreService.createOffer(rhId, offre);
        return ResponseEntity.ok(saved);
    }
}
