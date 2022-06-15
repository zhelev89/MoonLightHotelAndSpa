package team2.MoonLightHotelAndSpa.models.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "role", unique = true)
    private String role;

<<<<<<< HEAD
<<<<<<< HEAD
    @JsonBackReference
=======
>>>>>>> bea500f676d4b882bb701d147e9b0ae1841889cb
=======
    @JsonBackReference
>>>>>>> main
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
