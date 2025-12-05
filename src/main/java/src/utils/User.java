package src.utils;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String nom;

    @NotNull
    @Column(nullable = false)
    private String prenom;

    private LocalDate dateDeNaissance;

    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Pattern(
    	    regexp = "^[249][0-9]{7}$",
    	    message = "Le numéro doit contenir 8 chiffres et commencer par 2, 4 ou 9"
    	)
    	private String numeroTel;


    private String lieuResidence;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ROLE role;

    public User(String nom, String prenom, LocalDate dateNaissance, 
                String email, String numeroTel, String residence, ROLE role) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateNaissance;
        this.email = email;
        this.numeroTel = numeroTel;
        this.lieuResidence = residence;
        this.role = role;
    }
    public String getEmail() {
    	return this.email;
    }
    public String getNom() {
    	return this.nom;
    }
}
