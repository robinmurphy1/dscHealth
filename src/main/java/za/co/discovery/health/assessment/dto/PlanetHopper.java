package za.co.discovery.health.assessment.dto;

import java.util.List;

public class PlanetHopper {

    private int numberOfHops;
    private List<String> hopNames;
    private double distance;
    private double travelTime;

    public int getNumberOfHops() {
        return numberOfHops;
    }

    public void setNumberOfHops(int numberOfHops) {
        this.numberOfHops = numberOfHops;
    }

    public List<String> getHopNames() {
        return hopNames;
    }

    public void setHopNames(List<String> hopNames) {
        this.hopNames = hopNames;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(double travelTime) {
        this.travelTime = travelTime;
    }
}
