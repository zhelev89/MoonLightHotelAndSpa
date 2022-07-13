package team2.MoonLightHotelAndSpa.model.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomTitle title;

    @NotNull
    private String image;

    @NotNull
    private String[] images;

    @NotNull
    private String description;

    @NotNull
    private String facilities;

    @NotNull
    private Integer area;

    @NotNull
    private Integer people;

    @NotNull
    private Float price;

    // Questionable +-
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomView view;

    // Questionable +-
    @NotNull
    private Integer count;
}
