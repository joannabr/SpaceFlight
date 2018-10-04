package pl.joannabrania.spaceflight;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.joannabrania.spaceflight.database.FlightEntity;
import pl.joannabrania.spaceflight.database.TouristEntity;
import pl.joannabrania.spaceflight.database.repositories.FlightRepository;
import pl.joannabrania.spaceflight.database.repositories.TouristRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class SpaceflightApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpaceflightApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx, FlightRepository flightRepository, TouristRepository touristRepository) {
        return args -> {
            flightRepository.saveAll(Arrays.asList(FlightEntity.builder()
                            .price(100)
                            .numberOfSeats(100)
                            .arrivalTime(LocalDateTime.now())
                            .departureTime(LocalDateTime.now().plusHours(3))
                            .build(),
                    FlightEntity.builder()
                            .price(100)
                            .numberOfSeats(100)
                            .arrivalTime(LocalDateTime.now())
                            .departureTime(LocalDateTime.now().plusHours(3))
                            .build(),
                    FlightEntity.builder()
                            .price(780)
                            .numberOfSeats(50)
                            .arrivalTime(LocalDateTime.now().plusHours(5))
                            .departureTime(LocalDateTime.now().plusHours(14))
                            .build()
                    )
            );
            touristRepository.saveAll(Arrays.asList(TouristEntity.builder()
                            .name("Katarzyna")
                            .surname("Nowak")
                            .gender("female")
                            .country("Poland")
                            .notes("with children")
                            .dateOfBirth(LocalDate.now().minusYears(30))
                            .build(),
                    TouristEntity.builder()
                            .name("Kate")
                            .surname("Rain")
                            .gender("female")
                            .country("Germany")
                            .notes("seat next to window")
                            .dateOfBirth(LocalDate.now().minusYears(50))
                            .build(),
                    TouristEntity.builder()
                            .name("Anna")
                            .surname("Lopez")
                            .gender("female")
                            .country("Spain")
                            .notes("")
                            .dateOfBirth(LocalDate.now().minusYears(42))
                            .build()
                    )
            );


        };
    }
}
