package src.candidature;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import src.Offre.Offre;
import src.studet.Student;
import src.utils.StatutCandidature;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "offre_id"})
})
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offre_id", nullable = false)
    private Offre offre;

    private LocalDate datePostulation = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private StatutCandidature statut = StatutCandidature.EN_ATTENTE;
}
