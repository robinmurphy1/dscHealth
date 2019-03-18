package za.co.discovery.health.assessment.utils;

import za.co.discovery.health.assessment.dto.Node;
import za.co.discovery.health.assessment.repository.Planet;
import za.co.discovery.health.assessment.repository.PlanetData;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class GalaxyGraphCreator {

    public static List<Node> createNodes(List<Planet> planets) {

        return planets.stream()
                .map(node -> new Node(node.getPlanetNode()))
                .collect(Collectors.toList());
    }

    //NOTE: this could of been done with a join using entity mapping
    public static void mapDataToNodes(List<Node> nodeList, List<PlanetData> planetDataList) {

        for (Node node : nodeList) {
            for (PlanetData planetData : planetDataList) {
                if (node.getNodeName().equals(planetData.getPlanetOrigin())) {
                    try {
                        Node destNode = nodeList.stream()
                                .filter(n -> n.getNodeName().equals(planetData.getPlanetDestination()))
                                .findFirst()
                                .get();

                        node.addDestination(destNode, planetData.getDistance());
                    }catch (NoSuchElementException nseEx){
                        //TODO: just log - add tests to cover all permutation of hops
                    }

                }
            }
        }
    }

}
