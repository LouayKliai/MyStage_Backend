package src.studet;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import src.candidature.Candidature;

import src.universite.University;
import src.user.User;
import src.utils.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Student extends User {

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    private String niveau;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    @JsonBackReference
    private University university;

   

    @ElementCollection
    @JsonIgnore
    private List<String> skills = new ArrayList<>();

    /*@OneToMany(mappedBy = "student")
    private List<Stage> stages = new ArrayList<>();
    */
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Candidature> candidatures = new ArrayList<>();

    public Student(String nom, String prenom, LocalDate dateNaissance,
                   String email, String numeroTel, String residence,
                   ROLE role,String pwd, Specialite specialite, University university, String niveau) {

        super(nom, prenom, dateNaissance, email, numeroTel, residence, role,pwd);
        this.specialite = specialite;
        this.university = university;
        this.niveau = niveau;
    }
}
