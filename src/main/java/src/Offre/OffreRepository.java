package src.Offre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.societe.Societe;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {

    // Pour RH (par société)
    List<Offre> findBySociete(Societe societe);

    // Pour étudiant (optionnel : recherche par nom)
    List<Offre> findBySociete_Nom(String nom);
}
