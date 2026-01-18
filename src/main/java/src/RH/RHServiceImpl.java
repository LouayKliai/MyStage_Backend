package src.RH;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import src.DTO.RH.RHLoginDTO;
import src.DTO.RH.RHRegisterDTO;
import src.DTO.RH.RHResponseDTO;
import src.societe.Societe;
import src.societe.SocieteRepository;
import src.utils.ROLE;

@Service
@Transactional
public class RHServiceImpl implements RHService {

    private final RhRepository rhRepo;
    private final SocieteRepository societeRepo;
    private final PasswordEncoder passwordEncoder;

    public RHServiceImpl(RhRepository rhRepo, SocieteRepository societeRepo, PasswordEncoder passwordEncoder) {
        this.rhRepo = rhRepo;
        this.societeRepo = societeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RH register(RHRegisterDTO dto) {
        Societe societe = societeRepo.findById(dto.getSocieteId())
                .orElseThrow(() -> new RuntimeException("Société introuvable"));

        RH rh = new RH();
        rh.setFullname(dto.getFullname());
        rh.setEmail(dto.getEmail());
        rh.setPassword(passwordEncoder.encode(dto.getPassword()));
        rh.setRole(ROLE.RH);
        rh.setSociete(societe);

        return rhRepo.save(rh);
    }

    @Override
    public RHResponseDTO login(RHLoginDTO dto) {
        RH rh = rhRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email incorrect"));

        if(!passwordEncoder.matches(dto.getPassword(), rh.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return new RHResponseDTO(
                rh.getId(),
                rh.getFullname(),
                rh.getEmail(),
                rh.getSociete().getNom()
        );
    }
}
