package pl.joannabrania.spaceflight.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.joannabrania.spaceflight.database.FlightEntity;
import pl.joannabrania.spaceflight.services.FlightService;

import java.util.List;

@RestController
@CrossOrigin
public class FlightController {

    final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(value = "/flights", produces = "application/json")
    public ResponseEntity<List<FlightEntity>> listOfFlights() {
        return ResponseEntity.ok(flightService.getListOfFlights());
    }

    @PostMapping(value = "/flights", consumes = "application/json")
    public ResponseEntity saveFlight(@RequestBody FlightEntity flightEntity) {
        flightService.saveFlight(flightEntity);
        return ResponseEntity.ok(flightEntity);
    }

    @DeleteMapping(value = "/flights/{flightId}", produces = "application/json")
    public ResponseEntity deleteFlight(@PathVariable("flightId") int flightId) {
        flightService.removeFlight(flightId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/flights/{flightId}/tourists/{touristId}", consumes = "application/json")
    public ResponseEntity saveFlightToTourist(@PathVariable("flightId") int flightId, @PathVariable("touristId") int touristId) {
        flightService.saveTouristToFlight(flightId, touristId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/flights/{touristId}/tourists/{touristId}", produces = "application/json")
    public ResponseEntity deleteFlightFromTourist(@PathVariable("flightId") int touristId, @PathVariable("touristId") int flightId) {
        flightService.removeTourist(touristId, flightId);
        return ResponseEntity.ok().build();
    }

}
