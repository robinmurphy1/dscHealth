package za.co.discovery.health.assessment.dto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Node {

    private List<Node> shortestPath = new LinkedList<>();
    private Map<Node, Double> adjacentNodes = new HashMap();

    private String nodeName;
    private Double distance = Double.MAX_VALUE;

    public void addDestination(Node destination, Double distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    public Node(String nodeName, Double distance) {
        this.nodeName = nodeName;
        this.distance = distance;
    }

    public Node() {
    }

    public Map<Node, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(nodeName, node.nodeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeName);
    }
}
