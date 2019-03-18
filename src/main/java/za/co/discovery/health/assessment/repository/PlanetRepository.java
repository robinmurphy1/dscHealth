package za.co.discovery.health.assessment.repository;

import org.springframework.data.repository.CrudRepository;

//NOTE: would be best to exend CrudRepo to define update/save methods to limit calls to DB

public interface PlanetRepository extends CrudRepository<Planet, String> {
}
