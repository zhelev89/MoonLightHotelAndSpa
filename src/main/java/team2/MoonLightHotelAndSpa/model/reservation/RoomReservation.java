package team2.MoonLightHotelAndSpa.model.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.room.RoomBedType;
import team2.MoonLightHotelAndSpa.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_reservations")
public class RoomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant created;

    @NotNull
    @Column(name = "start_date")
    private Instant startDate;

    @NotNull
    @Column(name = "end_date")
    private Instant endDate;

    @NotNull
    @Column(name = "check_out")
    private Instant checkOut;

    @NotNull
    @Column(name = "adults")
    private Integer adults;

    @NotNull
    @Column(name = "kids")
    private Integer kids;

    @Column(name = "price")
    private Double price;

    @Enumerated(EnumType.STRING)
    private RoomBedType roomBedType;

    @NotNull
    @OneToOne
    private Room room;

    @NotNull
    @ManyToOne
    private User user;
}
