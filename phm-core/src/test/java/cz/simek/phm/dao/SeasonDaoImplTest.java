package cz.simek.phm.dao;

import cz.simek.phm.config.JpaTestConfig;
import cz.simek.phm.config.TestConfig;
import cz.simek.phm.model.Season;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by jenik on 8.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:SeasonDaoImplTestRunBefore.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:SeasonDaoImplTestRunAfter.sql")})
public class SeasonDaoImplTest {

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private ApplicationContext app;

    @Test
    public void findById() throws Exception {
        Season season = seasonDao.findById(1L);
        assertEquals(season.getId().longValue(), 1L);
        assertEquals(season.getName(),"importedSeasonName");
        assertEquals(season.getYears(),"2016/2017");
    }

    @Test
    @Rollback
    public void saveSeason(){
        Season season = createAndFillSeasonWithNullGames();
        seasonDao.saveNew(season);
        Season savedSeason = seasonDao.findByNameAndYears("savedSeason","2016/2017");
        assertEquals(season.getName(),savedSeason.getName());
        assertEquals(season.getYears(),savedSeason.getYears());
    }

    private Season createAndFillSeasonWithNullGames(){
        Season season = new Season();
        season.setName("savedSeason");
        season.setYears("2016/2017");
        return season;
    }




}