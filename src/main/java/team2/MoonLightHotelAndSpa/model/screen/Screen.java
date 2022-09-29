package team2.MoonLightHotelAndSpa.model.screen;

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
@Table(name = "screens")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String image;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private ScreenPosition position;

    @NotNull
    private Integer[] seats;
}
