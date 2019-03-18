package za.co.discovery.health.assessment.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANET_DATA")
public class PlanetData {

    @Id
    @Column(name = "ROUTE_ID")
    private Integer planetNode;

    @Column(name = "PLANET_ORIGIN")
    private String planetOrigin;

    @Column(name = "PLANET_DESTINATION")
    private String planetDestination;

    @Column(name = "DISTANCE")
    private double distance;

    @Column(name = "TRAFFIC_DELAY")
    private double trafficDelay;

    public PlanetData(Integer planetNode, String planetOrigin, String planetDestination, double distance, double trafficDelay) {
        this.planetNode = planetNode;
        this.planetOrigin = planetOrigin;
        this.planetDestination = planetDestination;
        this.distance = distance;
        this.trafficDelay = trafficDelay;
    }

    public PlanetData() {
    }

    public Integer getPlanetNode() {
        return planetNode;
    }

    public void setPlanetNode(Integer planetNode) {
        this.planetNode = planetNode;
    }

    public String getPlanetOrigin() {
        return planetOrigin;
    }

    public void setPlanetOrigin(String planetOrigin) {
        this.planetOrigin = planetOrigin;
    }

    public String getPlanetDestination() {
        return planetDestination;
    }

    public void setPlanetDestination(String planetDestination) {
        this.planetDestination = planetDestination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTrafficDelay() {
        return trafficDelay;
    }

    public void setTrafficDelay(double trafficDelay) {
        this.trafficDelay = trafficDelay;
    }
}

