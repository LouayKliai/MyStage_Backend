package src.universite;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;
import src.studet.Student;
import src.utils.STATE;
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "university_id")
    private UUID id;
    @Column(nullable = false, unique = true)
    private String nom;
    private String locale;
    private String domaine;
    
    

    @Enumerated(EnumType.STRING)
    private STATE etat;

    @OneToMany(mappedBy = "university",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JsonIgnore
    @JsonManagedReference
    private List<Student> listEtudiant = new ArrayList<>();

    public void addStudent(Student student) {
        listEtudiant.add(student);
        student.setUniversity(this);
    }
    public void removeStudent(Student student) {
        listEtudiant.remove(student);
        student.setUniversity(null);
    }
}
