package za.co.discovery.health.assessment.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.discovery.health.assessment.services.GalacticalDataImpl;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GalacticalDataController.class)
public class GalacticalDataControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    GalacticalDataImpl galacticalData;

    @Test
    public void getDistance() {
    }

    @Test
    public void getDistanceWithTraffic() {
    }

    @Test
    public void getPlanet() {
    }

    @Test
    public void getPlanet1() {
    }

    @Test
    public void savePlanet() {
    }

    @Test
    public void addPlanet() {
    }

    @Test
    public void deleteThePlanet() {
    }

    @Test
    public void getPlanetData() {
    }
}