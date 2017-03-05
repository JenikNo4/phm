package cz.simek.phm.config;

import cz.simek.phm.dao.*;
import cz.simek.phm.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jenik on 31.1.17.
 */
@Configuration
public class TestConfig {

    @Bean
    public UserService getUserService() {
        return new UserService();
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Bean
    public SeasonDao getSeasonDao() {
        return new SeasonDaoImpl();
    }

    @Bean
    public GameDao getGameDao() {
        return new GameDaoImpl();
    }

    @Bean
    public TeamDao getTeamDao() {
        return new TeamDaoImpl();
    }

    @Bean
    public PlayerDao getPlayerDao(){
        return new PlayerDaoImpl();
    }


}