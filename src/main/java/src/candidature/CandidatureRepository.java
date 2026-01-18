package src.candidature;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import src.Offre.Offre;
import src.studet.Student;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

	 Optional<Candidature> findByStudentAndOffre(Student student, Offre offre);


}
