package pl.joannabrania.spaceflight.database;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "flight")
@Builder
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flight_id")
    private int id;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {

                    CascadeType.ALL
            }
    )
    @JoinTable(
            name = "tourist_flight",
            joinColumns = {@JoinColumn(name = "flight_id")},
            inverseJoinColumns = {@JoinColumn(name = "tourist_id")}
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<TouristEntity> tourists;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "number_seats")
    private int numberOfSeats;

    private double price;
}
