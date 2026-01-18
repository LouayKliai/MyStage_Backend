package src.RH;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import src.DTO.RH.RHLoginDTO;
import src.DTO.RH.RHRegisterDTO;
import src.DTO.RH.RHResponseDTO;

@RestController
@RequestMapping("/rh")
public class RHController {

    private final RHService rhService;

    public RHController(RHService rhService) {
        this.rhService = rhService;
    }

    @PostMapping("/register")
    public ResponseEntity<RHResponseDTO> register(@RequestBody RHRegisterDTO dto) {
        RH rh = rhService.register(dto);
        return ResponseEntity.ok(new RHResponseDTO(rh.getId(), rh.getFullname(), rh.getEmail(), rh.getSociete().getNom()));
    }

    @PostMapping("/login")
    public ResponseEntity<RHResponseDTO> login(@RequestBody RHLoginDTO dto) {
        RHResponseDTO response = rhService.login(dto);
        return ResponseEntity.ok(response);
    }
}

