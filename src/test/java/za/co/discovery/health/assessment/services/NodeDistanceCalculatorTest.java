package za.co.discovery.health.assessment.services;

import org.junit.Before;
import org.junit.Test;
import za.co.discovery.health.assessment.dto.Graph;
import za.co.discovery.health.assessment.dto.Node;
import za.co.discovery.health.assessment.utils.NodeDistanceCalculator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class NodeDistanceCalculatorTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void calculateShortestPathFromSource_whenNodesAreHanging() {
    }

    @Test
    public void calculateShortestPathFromSource_whenNodesAreLinked() {

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10.00);
        nodeA.addDestination(nodeC, 15.00);

        nodeB.addDestination(nodeD, 12.00);
        nodeB.addDestination(nodeF, 15.00);

        nodeC.addDestination(nodeE, 10.00);

        nodeD.addDestination(nodeE, 2.00);
        nodeD.addDestination(nodeF, 1.00);

        nodeF.addDestination(nodeE, 5.00);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = NodeDistanceCalculator.calculateShortestPathFromSource(graph, nodeA);

        List<Node> shortestPathForNodeB = Arrays.asList(nodeA);
        List<Node> shortestPathForNodeC = Arrays.asList(nodeA);
        List<Node> shortestPathForNodeD = Arrays.asList(nodeA, nodeB);
        List<Node> shortestPathForNodeE = Arrays.asList(nodeA, nodeB, nodeD);
        List<Node> shortestPathForNodeF = Arrays.asList(nodeA, nodeB, nodeD);

        for (Node node : graph.getNodes()) {
            switch (node.getNodeName()) {
                case "B":
                    assertTrue(node
                            .getShortestPath()
                            .equals(shortestPathForNodeB));
                    break;
                case "C":
                    assertTrue(node
                            .getShortestPath()
                            .equals(shortestPathForNodeC));
                    break;
                case "D":
                    assertTrue(node
                            .getShortestPath()
                            .equals(shortestPathForNodeD));
                    break;
                case "E":
                    assertTrue(node
                            .getShortestPath()
                            .equals(shortestPathForNodeE));
                    break;
                case "F":
                    assertTrue(node
                            .getShortestPath()
                            .equals(shortestPathForNodeF));
                    break;
            }
        }
    }

    @Test
    public void calculateShortestPathReturnJourney_whenNodesAreLinked() {
    }

    @Test
    public void geShortestDistanceNode() {
    }

    @Test
    public void calculateMinimumDistance() {
    }
}