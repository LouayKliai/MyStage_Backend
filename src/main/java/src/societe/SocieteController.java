package src.societe;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/societes")
public class SocieteController {

    private final SocieteRepository societeRepo;

    public SocieteController(SocieteRepository societeRepo) {
        this.societeRepo = societeRepo;
    }

    @PostMapping
    public Societe create(@RequestBody Societe societe) {
        return societeRepo.save(societe);
    }

    @GetMapping
    public List<Societe> getAll() {
        return societeRepo.findAll();
    }
}
