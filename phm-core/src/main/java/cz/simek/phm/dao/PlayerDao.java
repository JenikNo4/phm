package cz.simek.phm.dao;

import cz.simek.phm.model.Player;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jenik on 23.2.17.
 */
@Transactional
public interface PlayerDao {

    Player findById(Long id);

    void saveNew(Player player);

    void updatePlayer(Player player);

    void deletePlayer(Player player);
}
