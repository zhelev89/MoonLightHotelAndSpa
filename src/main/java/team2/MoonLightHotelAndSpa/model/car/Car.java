package team2.MoonLightHotelAndSpa.model.car;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "brand")
    private String brand;

    @NotNull
    @Column(name = "model")
    private String model;

    @NotNull
    @Column(name = "image")
    private String image;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "images_id")
    private Set<CarImage> images;

    @NotNull
    @Column(name = "year")
    private int year;

    @CreationTimestamp
    @Column(name = "created")
    private Instant created;

    @ManyToOne
    private CarCategory carCategory;
}
