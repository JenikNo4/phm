package cz.simek.phm.dao;

import cz.simek.phm.config.JpaTestConfig;
import cz.simek.phm.config.TestConfig;
import cz.simek.phm.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jenik on 19.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:TeamDaoImplTestRunBefore.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:TeamDaoImplTestRunAfter.sql")})
public class TeamDaoImplTest {

    @Autowired
    TeamDao teamDao;

    @Test
    public void findById() throws Exception {
        Team teamById = teamDao.findById(1L);
        assertEquals(teamById.getId().longValue(),1L);
        assertEquals(teamById.getName(),"Team 1");
        assertEquals(teamById.getLogo(),"pathToLogo");
    }

    @Test
    public void findByNameWithLike(){
        List<Team> teamList = teamDao.findByNameWithLike("Team");
        assertNotNull(teamList);
        assertEquals(teamList.size(),2);
    }

    @Test
    public void save() throws Exception {
        teamDao.saveNew(createAndFillNewTeam());
        List<Team> teamList = teamDao.findByNameWithLike("Team 3");
        assertEquals(teamList.size(),1);
        Team team3 = teamList.get(0);
        assertEquals(team3.getName(),"Team 3");
        assertEquals(team3.getLogo(),"pathToLogo3");
        assertEquals(team3.getId().longValue(),3L);

    }

    @Test
    public void updateTeam() throws Exception {
        Team team2 = teamDao.findById(2L);
        team2.setLogo("anotherPathToLogo");
        teamDao.updateTeam(team2);
        Team team3updated = teamDao.findById(2L);
        assertEquals(team2.getName(),team3updated.getName());
        assertEquals(team3updated.getLogo(),"anotherPathToLogo");
        assertEquals(team3updated.getId().longValue(),2L);
    }

    @Test
    public void deleteTeam() throws Exception {
        Team team = teamDao.findById(1L);
        teamDao.deleteTeam(team);
        Team deletedTeam = teamDao.findById(1L);
        Team secondTeam = teamDao.findById(2L);
        assertEquals(deletedTeam,null);
        assertEquals(secondTeam.getGameListHomeTeam().get(0).getAwayTeam(),null);
    }

    private Team createAndFillNewTeam(){
        Team team = new Team();
        team.setName("Team 3");
        team.setLogo("pathToLogo3");
        return team;
    }

}