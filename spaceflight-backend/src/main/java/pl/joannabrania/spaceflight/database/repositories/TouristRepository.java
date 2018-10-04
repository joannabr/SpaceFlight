package pl.joannabrania.spaceflight.database.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.joannabrania.spaceflight.database.TouristEntity;

import java.util.List;

@Repository
public interface TouristRepository extends CrudRepository<TouristEntity, Integer> {
    List<TouristEntity> findAll();
}
