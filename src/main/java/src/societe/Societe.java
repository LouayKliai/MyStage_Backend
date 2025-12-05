package src.societe;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.RH.RH;
import src.stage.Stage;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Societe {

    @Id
    @Column(name = "matricule_social", nullable = false, unique = true)
    private String matriculeSocial;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String siege;

    @Column(nullable = false)
    private String secteur;

    @Column(nullable = false)
    private String capital;

    @OneToMany(
        mappedBy = "societe",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<RH> listeRh = new ArrayList<>();

    @OneToMany(
        mappedBy = "societe",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Stage> stages = new ArrayList<>();

    public Societe(String matricule, String nom, String siege, String secteur, String capital) {
        this.matriculeSocial = matricule;
        this.nom = nom;
        this.siege = siege;
        this.secteur = secteur;
        this.capital = capital;
    }

   
}
