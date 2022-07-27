package team2.MoonLightHotelAndSpa.model.room;

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
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomTitle title;

    @NotNull
    private String image;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "images_id")
    private Set<RoomImage> images;

    @NotNull
    private String description;

    @NotNull
    private String facilities;

    @NotNull
    private int area;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomView view;

    @NotNull
    private int people;

    @NotNull
    private float price;

    @NotNull
    private int count;
}
