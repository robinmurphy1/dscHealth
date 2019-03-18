package za.co.discovery.health.assessment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery.health.assessment.dto.PlanetHopper;
import za.co.discovery.health.assessment.repository.Planet;
import za.co.discovery.health.assessment.repository.PlanetData;
import za.co.discovery.health.assessment.services.GalacticalData;

import java.util.List;

@RestController
@RequestMapping("/v1/galaxy")
public class GalacticalDataController {

//    TODO: add swagger documentation
    //TODO: implement data validators

    @Autowired
    private GalacticalData galacticalData;

    @GetMapping(path = "/distance/{source}/{destination}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanetHopper> getDistance(@PathVariable("source") String source, @PathVariable("destination") String destination) {

        return ResponseEntity.ok(galacticalData.calculateDistanceToDestination(source.toUpperCase(), destination.toUpperCase()));
    }

    @GetMapping(path = "/distance/traffic/{source}/{destination}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanetHopper> getDistanceWithTraffic(@PathVariable("source") String source, @PathVariable("destination") String destination) {

        return ResponseEntity.ok(galacticalData.calculateDistanceToDestinationWithTraffic(source.toUpperCase(), destination.toUpperCase()));
    }

    @GetMapping(path = "/planets/{planetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Planet getPlanet(@PathVariable("planetId") String planetId) {

        return galacticalData.getPlanet(planetId);
    }

    @GetMapping(path = "/planets/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Planet>> getPlanet() {

        return ResponseEntity.ok(galacticalData.getPlanet());
    }

    @PutMapping(path = "/planets", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePlanet(@RequestBody Planet planet) {

        galacticalData.saveThePlanet(planet);
        return ResponseEntity.ok("You saved the planet");
    }

    @PostMapping(path = "/planets", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPlanet(@RequestBody Planet planet) {

        galacticalData.addAPlanet(planet);
        return ResponseEntity.ok("planet added");
    }

    @DeleteMapping(path = "/planets/{planetId}")
    public ResponseEntity<?> deleteThePlanet(@PathVariable("planetId") String planetId) {

        galacticalData.deleteThePlanet(planetId);
        return ResponseEntity.ok("planet destroyed");
    }

    @GetMapping(path = "/planet/data/{dataId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanetData> getPlanetData(@PathVariable("dataId") Integer dataId) {

        return ResponseEntity.ok(galacticalData.getPlanetData(dataId));
    }

}
