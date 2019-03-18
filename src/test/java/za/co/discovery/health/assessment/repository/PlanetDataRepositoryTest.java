package za.co.discovery.health.assessment.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//TODO: load test data from test data scripts & load data in setup

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlanetDataRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PlanetDataRepository planetDataRepository;

    @Test
    public void whenFindByDataId_thenReturnPlanetData() {

        //given
        PlanetData planetData = new PlanetData(100, "A", "A", 100.00, 30.67);
        testEntityManager.persistAndFlush(planetData);

        //when
        PlanetData planetDataFound = planetDataRepository.findById(planetData.getPlanetNode())
                .orElseThrow(() -> new EntityNotFoundException());

        //then
        assertThat(planetData.getPlanetNode()).isEqualTo(planetDataFound.getPlanetNode());
    }

    @Test
    public void whenFindAllData_thenReturnListOfData() {

        List<PlanetData> originalDataList = new ArrayList<>();
        planetDataRepository.findAll().forEach(originalDataList::add);

        //given
        PlanetData planetData = new PlanetData(100, "A", "B", 100.00, 30.67);
        PlanetData planetData1 = new PlanetData(101, "B", "C", 100.00, 30.67);
        PlanetData planetData2 = new PlanetData(102, "A", "C", 100.00, 30.67);
        testEntityManager.persistAndFlush(planetData);
        testEntityManager.persistAndFlush(planetData1);
        testEntityManager.persistAndFlush(planetData2);

        //when
        List<PlanetData> planetDataList = new ArrayList<>();
        planetDataRepository.findAll().forEach(planetDataList::add);

        //then
        assertThat(planetDataList.size())
                .isGreaterThan(originalDataList.size());

    }

    @Test(expected = PersistenceException.class)
    public void whenInsertDuplicateEntity_thenThrowPersistenceException() {

        //given
        PlanetData planetData = new PlanetData(1, "A", "B", 100.00, 30.67);
        testEntityManager.persistAndFlush(planetData);
    }

}