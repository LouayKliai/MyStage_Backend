package src.RH;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RhRepository extends JpaRepository<RH, UUID>{
	Optional<RH> findById(UUID rhId); 
	Optional<RH> findByEmail(String email);
	

}
