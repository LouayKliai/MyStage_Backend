package src.universite;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.studet.Student;
import src.utils.STATE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "university_id")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private String locale;

    @Column(nullable = false)
    private String domaine;

    @Column(nullable = false)
    private STATE etat;

    @OneToMany(
        mappedBy = "university",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Student> listEtudiant = new ArrayList<>();

    public University(String nom, String locale, String domaine, STATE etat) {
        this.nom = nom;
        this.locale = locale;
        this.domaine = domaine;
        this.etat = etat;
    }
}
