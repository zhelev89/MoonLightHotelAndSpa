package team2.MoonLightHotelAndSpa.model.reservation;

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
public class RoomReservation {

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
    @Column(name = "days")
    private Integer days;

    @NotNull
    @Column(name = "adults")
    private Integer adults;

    @NotNull
    @Column(name = "kids")
    private Integer kids;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "room_bed_type")
    private RoomBedType roomBedType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "view")
    private RoomView view;

    @NotNull
    @Column(name = "price")
    private Float price;

    @NotNull
    @CreationTimestamp
    @Column(name = "created")
    private Instant created;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @OneToOne
    private Room room;

    @NotNull
    @ManyToOne
    private User user;
}
