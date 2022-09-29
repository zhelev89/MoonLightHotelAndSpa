package team2.MoonLightHotelAndSpa.model.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.MoonLightHotelAndSpa.model.screen.Screen;
import team2.MoonLightHotelAndSpa.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "screen_reservations")
public class ScreenReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Instant date;

    @NotNull
    private Integer[] reservedSeats;

    @NotNull
    private double price;

    @NotNull
    private Screen screen;

    @NotNull
    private User user;

    private HashMap<Instant, Integer[]> freeSeats;

    public void setFreeSeats(Instant date, Integer[] seats) {
        freeSeats.put(date, seats);
    }
}
