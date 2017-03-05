package cz.simek.phm.dao;

import cz.simek.phm.model.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jenik on 8.2.17.
 */
@Repository

public class GameDaoImpl extends AbstractDao<Integer, Game> implements GameDao {

    public Game findById(Long id) {
        List<Game> gameList = getEntityManager().createQuery("SELECT g FROM Game g WHERE g.id=:id", Game.class).setParameter("id", id).getResultList();
        if(gameList.isEmpty()){
            return null;
        }else if (gameList.size()>1){
            //TODO implemnent LOGGING!!!
            System.out.println("LOG more than one record found - possible data inconsistency!");
        }
        return gameList.get(0);
    }

    public void saveNew(Game game) {
        persist(game);
    }

    public void updateGame(Game game){
        update(game);
    }

    public void deleteGame(Game game){
        delete(game);
    }
}
