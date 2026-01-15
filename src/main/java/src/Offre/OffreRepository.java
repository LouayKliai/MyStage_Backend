package src.Offre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepository extends JpaRepository<Offre, Integer> {

	  List<Offre> findBySocieteId(Long societeId);
}
