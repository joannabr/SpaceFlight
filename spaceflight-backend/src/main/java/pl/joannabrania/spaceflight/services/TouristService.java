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
public class TouristService {

    private final TouristRepository touristRepository;
    private final FlightRepository flightRepository;

    @Autowired
    public TouristService(TouristRepository touristRepository, FlightRepository flightRepository) {
        this.touristRepository = touristRepository;
        this.flightRepository = flightRepository;
    }

    public void removeTourist(int touristId){
        TouristEntity touristEntity = touristRepository.findById(touristId).get();
        Set<FlightEntity> listOfFlights = touristEntity.getFlightEntities();

        if(listOfFlights != null){
        for(FlightEntity flightEntity: listOfFlights){
            flightEntity.getTourists().remove(touristEntity);
            flightRepository.save(flightEntity);
            touristEntity.getFlightEntities().remove(flightEntity);
            }
        }

        touristRepository.save(touristEntity);
        touristRepository.delete(touristEntity);
    }

    public List<TouristEntity> getListOfTourists(){
        return touristRepository.findAll();
    }

    public void saveTourist(TouristEntity touristEntity){
        touristRepository.save(touristEntity);
    }

    public void saveFlight(int touristId, int flightId){
        FlightEntity flightEntity = findFlightEntity(flightId);
        TouristEntity touristEntity = findTouristEntity(touristId);

        flightEntity.getTourists().add(touristEntity);
        touristEntity.getFlightEntities().add(flightEntity);

        touristRepository.save(touristEntity);
        flightRepository.save(flightEntity);
    }

    public void removeFlight(int touristId, int flightId){
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
