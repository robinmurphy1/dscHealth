package za.co.discovery.health.assessment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.health.assessment.dto.Graph;
import za.co.discovery.health.assessment.dto.Node;
import za.co.discovery.health.assessment.dto.PlanetHopper;
import za.co.discovery.health.assessment.exceptions.DuplicateEntryException;
import za.co.discovery.health.assessment.repository.Planet;
import za.co.discovery.health.assessment.repository.PlanetData;
import za.co.discovery.health.assessment.repository.PlanetDataRepository;
import za.co.discovery.health.assessment.repository.PlanetRepository;
import za.co.discovery.health.assessment.utils.GalaxyGraphCreator;
import za.co.discovery.health.assessment.utils.GalaxyTravelCalculator;
import za.co.discovery.health.assessment.utils.NodeDistanceCalculator;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO: CRUD operations need to be implemented for PlanetData
//NOTE: some of the functions

@Service
public class GalacticalDataImpl implements GalacticalData {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private PlanetDataRepository planetDataRepository;

    @Override
    public PlanetHopper calculateDistanceToDestination(String source, String destination) {

        return calculateDistanceToDestination(source, destination, Boolean.FALSE);
    }

    @Override
    public PlanetHopper calculateDistanceToDestinationWithTraffic(String source, String destination) {

        return calculateDistanceToDestination(source, destination, Boolean.TRUE);
    }


    @Override
    public List<Planet> getPlanet() {

        List<Planet> planetList = new ArrayList<>();
        planetRepository.findAll().forEach(planetList::add);

        return planetList;
    }

    @Override
    public Planet getPlanet(String nodeId) {

        return planetRepository.findById(nodeId)
                .orElseThrow(() -> new EntityNotFoundException(nodeId));
    }

    @Override
    public void addAPlanet(Planet planet) {

        if (!planetRepository.existsById(planet.getPlanetNode())) {
            planetRepository.save(planet);
        } else {
            throw new DuplicateEntryException(planet.getPlanetName());
        }
    }

    @Override
    public void deleteThePlanet(String planetId) {

        getPlanet(planetId);
        planetRepository.deleteById(planetId);
    }

    @Override
    public void saveThePlanet(Planet planet) {

        getPlanet(planet.getPlanetNode());
        planetRepository.save(planet);
    }

    @Override
    public PlanetData getPlanetData(Integer planetId) {

        return planetDataRepository.findById(planetId)
                .orElseThrow(() -> new EntityNotFoundException(Integer.toString(planetId)));
    }

    @Override
    public List<PlanetData> getPlanetData(boolean traffic) {

        List<PlanetData> planetDataList = new ArrayList<>();
        planetDataRepository.findAll().forEach(planetDataList::add);

        if (traffic) {
            planetDataList.forEach(p -> p.setDistance(p.getDistance() + p.getTrafficDelay()));
        }

        return planetDataList;
    }

    //This function is so rocket man can find the shortest way back from where he came from
    public List<PlanetData> mergePlanetData(boolean traffic) {

        //create deep copy
        List<PlanetData> planetDataList;
        planetDataList = getPlanetData(traffic).stream()
                .map(p -> new PlanetData(p.getPlanetNode(), p.getPlanetOrigin(), p.getPlanetDestination(), p.getDistance(), p.getTrafficDelay()))
                .collect(Collectors.toList());


        //TODO: get highest id and then increment
        int idIncrement = 50;

        // reverse src & direction
        for (PlanetData data : planetDataList) {
            String tmpDest = data.getPlanetDestination();
            data.setPlanetDestination(data.getPlanetOrigin());
            data.setPlanetOrigin(tmpDest);
            data.setPlanetNode(++idIncrement);
        }

        planetDataList.addAll(getPlanetData(traffic));

        return planetDataList;
    }


    PlanetHopper calculateDistanceToDestination(String source, String destination, boolean traffic) {
        Graph planetGraph = new Graph();

        List<Node> nodeList = GalaxyGraphCreator.createNodes(getPlanet());
        GalaxyGraphCreator.mapDataToNodes(nodeList, mergePlanetData(traffic));

        for (Node node : nodeList) {
            planetGraph.addNode(node);
        }

        NodeDistanceCalculator.calculateShortestPathFromSource(planetGraph, findNodeFromCode(source, nodeList));

        return getTravelData(planetGraph, destination);
    }

    PlanetHopper getTravelData(Graph planetGraph, String destination) {

        Node destinationNode = planetGraph.getNodes()
                .stream()
                .filter(n -> n.getNodeName().equals(destination))
                .findFirst()
                .get();

        PlanetHopper planetHopper = new PlanetHopper();

        planetHopper.setDistance(destinationNode.getDistance());
        planetHopper.setNumberOfHops(destinationNode.getShortestPath().size());
        planetHopper.setTravelTime(GalaxyTravelCalculator.calculateTravelTimeByDaysWithoutTraffic(destinationNode.getDistance()));

        List<String> hopNames = destinationNode.getShortestPath()
                .stream()
                .map(p -> p.getNodeName())
                .collect(Collectors.toList());

        planetHopper.setHopNames(hopNames);

        return planetHopper;
    }

    Node findNodeFromCode(String planetCode, List<Node> nodeList) {
        return nodeList.stream()
                .filter(n -> n.getNodeName().equals(planetCode))
                .findFirst()
                .get();
    }
}
