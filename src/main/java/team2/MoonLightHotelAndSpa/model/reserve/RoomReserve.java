package team2.MoonLightHotelAndSpa.model.reserve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.room.RoomBedType;
import team2.MoonLightHotelAndSpa.model.room.RoomView;
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
public class RoomReserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_date")
    private Instant startDate;

    @NotNull
    @Column(name = "end_date")
    private Instant endDate;

    @NotNull
    private Integer days;

    @NotNull
    private Integer adults;

    @NotNull
    private Integer kids;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomBedType roomBedType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomView roomView;

    @NotNull
    private Float price;

    @CreationTimestamp
    private Instant created;

//    @NotNull
//    @Column(name = "status")
//    private String status;

    @NotNull
    @ManyToOne
    private Room room;

    @NotNull
    @ManyToOne
    private User user;
}
