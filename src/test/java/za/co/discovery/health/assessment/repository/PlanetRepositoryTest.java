package za.co.discovery.health.assessment.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PlanetRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PlanetRepository planetRepository;

    @Test
    public void whenFindByPlanetId_thenReturnPlanet() {

        //given
        Planet planet = new Planet("AX", "my planet");
        testEntityManager.persistAndFlush(planet);

        //when
        Planet planetFound = planetRepository.findById(planet.getPlanetNode())
                .orElseThrow(() -> new EntityNotFoundException());

        //then
        assertThat(planetFound.getPlanetNode())
                .isEqualTo(planet.getPlanetNode());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFindByPlanetId_notFound_thenThrowException() {

        //given
        Planet planet = new Planet("AX", "my planet");
        testEntityManager.persist(planet);
        testEntityManager.flush();

        //when
        Planet planetFound = planetRepository.findById("ab")
                .orElseThrow(() -> new EntityNotFoundException());

    }

}