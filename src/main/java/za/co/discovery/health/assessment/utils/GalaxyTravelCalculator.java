package za.co.discovery.health.assessment.utils;

public class GalaxyTravelCalculator {

    private static final Double LIGHT_YEAR_CONSTANT = 9460730472580800.00;
    private static final Double TRAVEL_SPEED = 7500000000000.00;
    private static final int SECONDS_PER_24HR = 60 * 60 * 24;


    public static Double calculateTravelTimeByDaysWithoutTraffic(Double lightYearDistance) {

        return ((lightYearDistance * LIGHT_YEAR_CONSTANT) / TRAVEL_SPEED) / SECONDS_PER_24HR;

    }

    public static Double calculateTravelTimeByDaysWithTraffic(Double lightYearDistance, Double trafficDelay) {

        return (((lightYearDistance + trafficDelay) * LIGHT_YEAR_CONSTANT) / TRAVEL_SPEED) / SECONDS_PER_24HR;
    }
}
