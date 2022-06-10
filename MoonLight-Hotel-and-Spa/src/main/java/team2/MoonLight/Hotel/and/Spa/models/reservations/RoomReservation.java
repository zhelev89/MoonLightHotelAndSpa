package team2.MoonLight.Hotel.and.Spa.models.reservations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.MoonLight.Hotel.and.Spa.models.rooms.Room;
import team2.MoonLight.Hotel.and.Spa.models.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Column(name = "created_at")
    private Date createdAt;

    @NotNull
    @Column(name = "check_in")
    private Date checkIn;

    @NotNull
    @Column(name = "check_out")
    private Date checkOut;

    @NotNull
    @Column(name = "guest_number")
    private Integer guestNumber;

    @NotNull
    @ManyToOne
    private Room room;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
