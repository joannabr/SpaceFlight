package pl.joannabrania.spaceflight.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.joannabrania.spaceflight.database.TouristEntity;
import pl.joannabrania.spaceflight.services.TouristService;

import java.util.List;

@Controller
@CrossOrigin
public class TouristController {

    final TouristService touristService;

    @Autowired
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping(value = "/tourists", produces = "application/json")
    public ResponseEntity<List<TouristEntity>> listOfTourist() {
        return ResponseEntity.ok(touristService.getListOfTourists());
    }

    @PostMapping(value = "/tourists", consumes = "application/json")
    public ResponseEntity saveTourist(@RequestBody TouristEntity touristEntity) {
        touristService.saveTourist(touristEntity);
        return ResponseEntity.ok(touristEntity);
    }

    @DeleteMapping(value = "/tourists/{touristId}", produces = "application/json")
    public ResponseEntity deleteTourist(@PathVariable("touristId") int touristId) {
        touristService.removeTourist(touristId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/tourists/{touristId}/flights/{flightId}", consumes = "application/json")
    public ResponseEntity saveTouristToFlight(@PathVariable("touristId") int touristId, @PathVariable("flightId") int flightId) {
        touristService.saveFlight(touristId, flightId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/tourists/{touristId}/flights/{flightId}", produces = "application/json")
    public ResponseEntity deleteTouristFromFlight(@PathVariable("touristId") int touristId, @PathVariable("flightId") int flightId) {
        touristService.removeFlight(touristId, flightId);
        return ResponseEntity.ok().build();
    }
}
