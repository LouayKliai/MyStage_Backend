package src.RH;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.societe.Societe;
import src.utils.ROLE;
import src.utils.User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RH extends User {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "societe_id", nullable = false)
    private Societe societe;

    public RH(String nom, String prenom, LocalDate dateNaissance,
              String email, String numeroTel, String residence,
              ROLE role, Societe societe) {

        super(nom, prenom, dateNaissance, email, numeroTel, residence, role);
        this.societe = societe;
    }
}
