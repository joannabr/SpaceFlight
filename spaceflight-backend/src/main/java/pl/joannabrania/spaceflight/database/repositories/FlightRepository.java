package pl.joannabrania.spaceflight.database.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.joannabrania.spaceflight.database.FlightEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends CrudRepository<FlightEntity, Integer> {
    List<FlightEntity> findAll();

    Optional<FlightEntity> findById(Integer id);
}
