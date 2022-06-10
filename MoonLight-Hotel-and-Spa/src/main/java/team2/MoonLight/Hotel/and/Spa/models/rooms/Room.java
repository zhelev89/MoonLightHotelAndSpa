package team2.MoonLight.Hotel.and.Spa.models.rooms;

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
    @ManyToOne
    private RoomType roomType;

    @NotNull
    @ManyToOne
    private RoomView roomView;

    @NotNull
    @ManyToOne
    private RoomBedType roomBedType;
}
