package pl.joannabrania.spaceflight.models.forms;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FlightForm {

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int numberOfSeats;
    private double price;

}
