package pl.joannabrania.spaceflight.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.joannabrania.spaceflight.database.FlightEntity;
import pl.joannabrania.spaceflight.database.TouristEntity;
import pl.joannabrania.spaceflight.database.repositories.FlightRepository;
import pl.joannabrania.spaceflight.database.repositories.TouristRepository;

import java.util.List;
import java.util.Set;

@Service
public class FlightService {

    final FlightRepository flightRepository;
    final TouristRepository touristRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, TouristRepository touristRepository) {
        this.flightRepository = flightRepository;
        this.touristRepository = touristRepository;
    }

    public void removeFlight(int flightId){
        FlightEntity flightEntity = flightRepository.findById(flightId).get();
        Set<TouristEntity> listOfTourist = flightEntity.getTourists();

        if(listOfTourist != null){
        for(TouristEntity touristEntity: listOfTourist){
            touristEntity.getFlightEntities().remove(flightEntity);
            touristRepository.save(touristEntity);
            flightEntity.getTourists().remove(touristEntity);
            }
        }

        flightRepository.save(flightEntity);
        flightRepository.delete(flightEntity);
    }

    public List<FlightEntity> getListOfFlights(){
        return flightRepository.findAll();
    }

    public void saveFlight(FlightEntity flightEntity){
        flightRepository.save(flightEntity);
    }

    public void saveTouristToFlight(int flightId, int touristId){
        FlightEntity flightEntity = findFlightEntity(flightId);
        TouristEntity touristEntity = findTouristEntity(touristId);

        flightEntity.getTourists().add(touristEntity);
        touristEntity.getFlightEntities().add(flightEntity);

        touristRepository.save(touristEntity);
        flightRepository.save(flightEntity);
    }

    public void removeTourist(int flightId, int touristId){
        FlightEntity flightEntity = findFlightEntity(flightId);
        TouristEntity touristEntity = findTouristEntity(touristId);

        touristEntity.getFlightEntities().remove(flightEntity);
        flightEntity.getTourists().remove(touristEntity);

        flightRepository.save(flightEntity);
        touristRepository.save(touristEntity);
    }

    public TouristEntity findTouristEntity(int touristId){
        return touristRepository.findById(touristId)
                .orElseThrow(() -> new RuntimeException("No tourists in database with id: " + touristId));
    }

    public FlightEntity findFlightEntity(int flightId){
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("No flights in database with id: " + flightId));
    }
}
