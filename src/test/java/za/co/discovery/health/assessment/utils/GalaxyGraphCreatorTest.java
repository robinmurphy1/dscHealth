package za.co.discovery.health.assessment.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.discovery.health.assessment.dto.Graph;
import za.co.discovery.health.assessment.dto.Node;
import za.co.discovery.health.assessment.repository.Planet;
import za.co.discovery.health.assessment.repository.PlanetData;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class GalaxyGraphCreatorTest {

    @Before
    public void setUp() {

    }

    @Test
    public void mapNodeListToDataAndAddToGraph() {

        //given
        Graph graph = new Graph();
        List<Node> nodeList = GalaxyGraphCreator.createNodes(createPlanetList());

        // when
        GalaxyGraphCreator.mapDataToNodes(nodeList, createPlanetDataList());

        for (Node node : nodeList) {
            graph.addNode(node);
        }

        //Then
        assertThat(graph.getNodes().size())
                .isEqualTo(nodeList.size());
    }

    @Test
    public void whenPlanetListIsRetrieved_thenCreateNodeList() {

        //given
        List<Node> nodeList = GalaxyGraphCreator.createNodes(createPlanetList());

        assertThat(nodeList.size())
                .isEqualTo(createPlanetList().size());
    }

    @Test
    public void whenPlanetDatListRetrieved_BuildGraphWithData() {

        List<Node> nodeList = GalaxyGraphCreator.createNodes(createPlanetList());
        GalaxyGraphCreator.mapDataToNodes(nodeList, createPlanetDataList());

        assertThat(nodeList).isNotNull();
    }

    List<Planet> createPlanetList() {

        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet("A", "a planet"));
        planets.add(new Planet("B", "another planet"));
        planets.add(new Planet("C", "a far planet"));
        planets.add(new Planet("D", "my planet"));
        planets.add(new Planet("E", "my planet"));
        planets.add(new Planet("F", "my planet"));

        return planets;
    }

    List<PlanetData> createPlanetDataList() {

        List<PlanetData> planetDataList = new ArrayList<>();

        planetDataList.add(new PlanetData(100, "A", "B", 30.00, 45.00));
        planetDataList.add(new PlanetData(101, "A", "C", 30.00, 23));
        planetDataList.add(new PlanetData(102, "A", "D", 23, 54));
        planetDataList.add(new PlanetData(103, "B", "E", 56, 67));
        planetDataList.add(new PlanetData(104, "B", "F", 78, 6));
        planetDataList.add(new PlanetData(105, "C", "E", 34, 6));
        planetDataList.add(new PlanetData(106, "D", "E", 7000, 6));
        planetDataList.add(new PlanetData(106, "D", "B", 3, 6));
        planetDataList.add(new PlanetData(107, "A", "F", 150, 6));
        planetDataList.add(new PlanetData(108, "E", "F", 3, 6));

        return planetDataList;
    }

}