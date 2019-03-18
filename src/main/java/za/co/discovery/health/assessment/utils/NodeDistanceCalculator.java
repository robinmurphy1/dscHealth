package za.co.discovery.health.assessment.utils;

import za.co.discovery.health.assessment.dto.Graph;
import za.co.discovery.health.assessment.dto.Node;
import za.co.discovery.health.assessment.exceptions.NodeException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//NOTE: calculation takes into consideration to destination and return journey from any of the nodes
public class NodeDistanceCalculator {

    //TODO: add logging
    //TODO: 'hanging' nodes were removed from data - need to replace them

    private NodeDistanceCalculator() {
        throw new IllegalStateException("Utility class");
    }

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {

        source.setDistance(0.00);

        Set<Node> unsettledNodes = new HashSet<>();
        Set<Node> settledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {

            Node currentNode = geShortestDistanceNode(unsettledNodes);

            if (currentNode == null) {
                throw new NodeException("Not a valid node");
            }

            unsettledNodes.remove(currentNode);

            for (Map.Entry<Node, Double> adjacentPair : currentNode.getAdjacentNodes().entrySet()) {

                Node adjacentNode = adjacentPair.getKey();
                Double edgeWeight = adjacentPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(currentNode, adjacentNode, edgeWeight);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }

        return graph;
    }

    static Node geShortestDistanceNode(Set<Node> unsettledNodes) {

        Node shortestDistanceNode = null;
        Double shortestDistance = Double.MAX_VALUE;

        for (Node node : unsettledNodes) {
            if (node.getDistance() < shortestDistance) {
                shortestDistance = node.getDistance();
                shortestDistanceNode = node;
            }
        }

        return shortestDistanceNode;
    }

    static void calculateMinimumDistance(Node sourceNode, Node adjacentNode, Double edgeWeight) {

        if (sourceNode.getDistance() + edgeWeight < adjacentNode.getDistance()) {
            adjacentNode.setDistance(sourceNode.getDistance() + edgeWeight);

            List<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            adjacentNode.setShortestPath(shortestPath);
        }
    }
}
