package src.user;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import src.utils.ROLE;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID id;

    @NotNull private String fullname;
    

    private LocalDate dateDeNaissance;

    @Email @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^[249][0-9]{7}$")
    private String numeroTel;

    private String lieuResidence;

    @Enumerated(EnumType.STRING)
    private ROLE role;

    @Column(nullable = false)
    private String password;

    public User(String fullname, LocalDate dateNaissance,
                String email, String numeroTel, String residence, ROLE role,String pwd) {

        this.fullname = fullname;        
        this.dateDeNaissance = dateNaissance;
        this.email = email;
        this.numeroTel = numeroTel;
        this.lieuResidence = residence;
        this.role = role;
        this.password=pwd;
    }
}
