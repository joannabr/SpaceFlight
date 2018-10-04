package pl.joannabrania.spaceflight.database;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "tourist")
@Builder
public class TouristEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tourist_id")
    private int id;
    private String name;
    private String surname;
    private String gender;
    private String country;
    private String notes;
    @Column(name = "date_birth")
    private LocalDate dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.ALL
            },
            mappedBy = "tourists")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<FlightEntity> flightEntities;
}
