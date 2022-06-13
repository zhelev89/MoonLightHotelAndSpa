package team2.MoonLight.Hotel.and.Spa.models.reservations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import team2.MoonLight.Hotel.and.Spa.models.rooms.RoomBedType;
import team2.MoonLight.Hotel.and.Spa.models.rooms.Room;
import team2.MoonLight.Hotel.and.Spa.models.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

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

    @NotNull
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @NotNull
    @Column(name = "check_in")
    private Instant checkIn;

    @NotNull
    @Column(name = "check_out")
    private Instant checkOut;

    @NotNull
    @Column(name = "guest_number")
    private Integer guestNumber;

    @NotNull
    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private RoomBedType roomBedType;

    @NotNull
    @OneToOne
    private Room room;

    @NotNull
    @ManyToOne
    private User user;
}
