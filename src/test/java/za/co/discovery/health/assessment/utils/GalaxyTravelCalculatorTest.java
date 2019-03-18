package za.co.discovery.health.assessment.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

//TODO: - cover edge case tests

@RunWith(SpringRunner.class)
public class GalaxyTravelCalculatorTest {


    @Test
    public void calculateTravelTimeByDaysWithoutTraffic() {

        Double travellingTime = GalaxyTravelCalculator.calculateTravelTimeByDaysWithoutTraffic(45.00);
        assertThat(travellingTime)
                .isGreaterThan(0.00);

    }

    @Test
    public void calculateTravelTimeByDaysWithTraffic() {

        Double travellingTime = GalaxyTravelCalculator.calculateTravelTimeByDaysWithTraffic(45.00, 8.9);
        assertThat(travellingTime)
                .isGreaterThan(GalaxyTravelCalculator.calculateTravelTimeByDaysWithoutTraffic(45.00));
    }


}