package src.RH;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.societe.Societe;

import src.user.User;
import src.utils.ROLE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RH extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "societe_id", nullable = false)
    private Societe societe;

   
    public RH(String nom, String prenom, LocalDate dateNaissance,
              String email, String numeroTel, String residence,
              ROLE role, String password, Societe societe) {

        super(nom, prenom, dateNaissance, email, numeroTel, residence, role, password);
        this.societe = societe;
    }
}
