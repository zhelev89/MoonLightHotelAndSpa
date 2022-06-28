package team2.MoonLightHotelAndSpa.models.rooms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

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

    //Ако е само List не стартира проекта
    @NotNull
    private ArrayList<String> images;

    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    private RoomFacilities facilities;

    @NotNull
    private Integer area;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomView view;

    @NotNull
    private Integer people;

    @NotNull
    @Column(name = "price")
    private Float price;
}
