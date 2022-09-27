package team2.MoonLightHotelAndSpa.model.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import team2.MoonLightHotelAndSpa.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_reservations")
public class TableReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "date")
    private Instant date;

    @CreationTimestamp
    private Instant updated;

    @NotNull
    private int people;

    @NotNull
    private double price;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @ManyToOne
    private team2.MoonLightHotelAndSpa.model.table.Table table;

    @NotNull
    @ManyToOne
    private User user;
}
