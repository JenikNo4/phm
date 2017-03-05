package cz.simek.phm.dao;

import cz.simek.phm.config.JpaTestConfig;
import cz.simek.phm.config.TestConfig;
import cz.simek.phm.enums.Position;
import cz.simek.phm.enums.StickSide;
import cz.simek.phm.model.Player;
import cz.simek.phm.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by jenik on 28.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:PlayerDaoImplTestRunBefore.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:PlayerDaoImplTestRunAfter.sql")})
public class PlayerDaoImplTest {

    @Autowired
    private PlayerDao playerDao;

    @Test
    public void findById() throws Exception {
        Player player = playerDao.findById(1L);
        assertEquals(player.getId().longValue(), 1L);
        assertEquals(player.getName(),"Jan");
        assertEquals(java.util.Optional.ofNullable(player.getJersey()), java.util.Optional.ofNullable(4));
    }

    @Test
    @Rollback
    public void saveNew() throws Exception {
        playerDao.saveNew(createAndFillNewPlayer());
        Player player = playerDao.findById(2L);
        assertEquals(player.getId().longValue(), 2L);
        assertEquals(player.getName(),"Oskar");
        assertEquals(player.getActualTeam().getName(),"Team 1");
    }

    @Test
    @Rollback
    public void updatePlayer() throws Exception {
        Player player = playerDao.findById(1L);
        player.setSurename("UPDATED");
        playerDao.updatePlayer(player);
        Player updatedPlayer = playerDao.findById(1L);
        assertEquals(updatedPlayer.getSurename(), "UPDATED");
        assertEquals(player.getName(),"Jan");
    }

    @Test
    public void deletePlayer() throws Exception {
        Player player = playerDao.findById(1L);
        playerDao.deletePlayer(player);
        Player playerDeleted = playerDao.findById(1L);
        assertEquals(playerDeleted, null);
    }

    private Player createAndFillNewPlayer() {
        Date date = new GregorianCalendar(1985, 05, 16).getTime();
        Player player = new Player();
        player.setBirthDate(date);
        player.setBirthPlace("Praha");
        player.setImagePath("/himImage.jpg");
        player.setJersey(7);
        player.setPosition(Position.DEFENSE);
        player.setStick(StickSide.RIGHT);
        player.setName("Oskar");
        player.setSurename("Novak");
        Team team = new Team();
        team.setId(1L);
        player.setActualTeam(team);

        return player;
    }

}