package src.studet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.stage.Stage;
import src.universite.University;
import src.utils.ROLE;
import src.utils.Specialite;
import src.utils.User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student extends User {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialite specialite;

    @Column(nullable = false)
    private String niveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @OneToMany(
        mappedBy = "student",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Stage> stages = new ArrayList<>();


    public Student(
        String nom,
        String prenom,
        LocalDate dateNaissance,
        String email,
        String numeroTel,
        String residence,
        ROLE role,
        Specialite specialite,
        University university,
        String niveau
    ) {
        super(nom, prenom, dateNaissance, email, numeroTel, residence, role);
        this.specialite = specialite;
        this.university = university;
        this.niveau = niveau;
    }
   
}
