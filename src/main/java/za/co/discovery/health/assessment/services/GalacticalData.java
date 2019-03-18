package za.co.discovery.health.assessment.services;

import za.co.discovery.health.assessment.dto.PlanetHopper;
import za.co.discovery.health.assessment.repository.Planet;
import za.co.discovery.health.assessment.repository.PlanetData;

import java.util.List;

public interface GalacticalData {

    Planet getPlanet(String nodeId);

    void saveThePlanet(Planet planet);

    void addAPlanet(Planet planet);

    void deleteThePlanet(String planetId);

    PlanetData getPlanetData(Integer planetId);

    List<PlanetData> getPlanetData(boolean traffic);

    List<Planet> getPlanet();

    PlanetHopper calculateDistanceToDestination(String source, String destination);

    PlanetHopper calculateDistanceToDestinationWithTraffic(String source, String destination);

}
