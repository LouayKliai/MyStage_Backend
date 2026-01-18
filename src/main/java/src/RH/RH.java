package src.RH;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.Offre.Offre;
import src.societe.Societe;

import src.user.User;
import src.utils.ROLE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RH extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "societe_id", nullable = false)
    @JsonIgnore
    private Societe societe;
    
    @OneToMany(mappedBy = "rh", cascade = CascadeType.ALL)
    private List<Offre> offres = new ArrayList<>();
}