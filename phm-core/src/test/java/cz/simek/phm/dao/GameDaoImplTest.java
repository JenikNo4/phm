package cz.simek.phm.dao;

import cz.simek.phm.config.JpaTestConfig;
import cz.simek.phm.config.TestConfig;
import cz.simek.phm.model.Game;
import cz.simek.phm.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by jenik on 8.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:GameDaoImplTestRunBefore.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:GameDaoImplTestRunAfter.sql")})
public class GameDaoImplTest {

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private GameDao gameDao;

    @Autowired
    private ApplicationContext app;

    @Test
    @Rollback
    public void findById() throws Exception {
        Game game = gameDao.findById(1L);
        assertEquals(game.getId().longValue(), 1L);
        assertEquals(game.getHomeTeam().getId().longValue(), 2L);
        assertEquals(game.getHomeTeam().getLogo(), "pathToLogo2");
        assertEquals(game.getHomeTeam().getName(), "Team 2");

    }

    @Test
    public void saveGame(){
        Game game = createGame();
        gameDao.saveNew(game);
        Assert.notNull(game.getId());
    }

    @Test
    @Rollback
    public void saveAndUpdateGame() {
        Game game = createGame();
        gameDao.saveNew(game);
        Game game1 = gameDao.findById(game.getId());
        assertEquals(game1.getHomeTeam().getId().longValue(),game.getHomeTeam().getId().longValue());
        assertEquals(game1.getAwayScore(),0);

        //set new Score
        game1.setHomeScore(6);
        game1.setAwayScore(2);
        gameDao.updateGame(game1);

        Game game2 = gameDao.findById(game1.getId());
        assertEquals(game2.getHomeTeam().getId().longValue(),game.getHomeTeam().getId().longValue());
        assertEquals(game2.getAwayScore(),2);
        assertEquals(game2.getHomeScore(),6);

    }

    @Test
    @Rollback
    public void deleteGame(){
        Game game = gameDao.findById(1L);
        gameDao.deleteGame(game);
        Game game1 = gameDao.findById(1L);
        assertEquals(game1,null);
    }

    public void findByIdDataInconsistency(){
        //TODO implement rainy test
    }


    private Game createGame() {
        Game game = new Game();
        game.setSeason(seasonDao.findById(1L));
        game.setDate(new Date());
        game.setHomeTeam(createHomeTeam());
        game.setAwayTeam(createAwayTeam());
        return game;
    }

    private Team createHomeTeam() {
        Team team = new Team();
        team.setId(1L);
        return team;
    }

    private Team createAwayTeam() {
        Team team = new Team();
        team.setId(2L);
        return team;
    }
}