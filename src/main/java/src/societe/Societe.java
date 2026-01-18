package src.societe;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import src.RH.RH;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Societe {

    @Id
    @Column(name = "matricule_social", nullable = false, unique = true)
    private String matriculeSocial;

    @Column(nullable = false) private String nom;
    @Column(nullable = false) private String siege;
    @Column(nullable = false) private String secteur;
    @Column(nullable = false) private String capital;

    @OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
    private List<RH> listeRh = new ArrayList<>();
    public String getId() {
    	return this.matriculeSocial;
    }

}
