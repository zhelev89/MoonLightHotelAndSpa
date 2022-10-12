package team2.MoonLightHotelAndSpa.model.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import team2.MoonLightHotelAndSpa.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "car_transfers")
public class CarTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "price")
    private double price;

    @NotNull
    @Column(name = "date")
    private Instant date;

    @CreationTimestamp
    @Column(name = "created")
    private Instant created;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @ManyToOne
    private Car car;

    @NotNull
    @ManyToOne
    private User user;
}
