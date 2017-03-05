package cz.simek.phm.dao;

import cz.simek.phm.model.Game;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jenik on 31.1.17.
 */
@Transactional
public interface GameDao {

    Game findById(Long id);

    void saveNew(Game game);

    void updateGame(Game game);

    void deleteGame(Game game);

}
