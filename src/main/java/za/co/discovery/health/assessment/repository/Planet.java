package za.co.discovery.health.assessment.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PLANETS")
public class Planet {

    @Id
    @Column(name = "PLANET_NODE")
    private String planetNode;

    @Column(name = "PLANET_NAME")
    private String planetName;

    public Planet() {
    }

    public Planet(String planetNode, String planetName) {

        this.planetNode = planetNode;
        this.planetName = planetName;
    }

    public String getPlanetNode() {
        return planetNode;
    }

    public void setPlanetNode(String planetNode) {
        this.planetNode = planetNode;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
}