package cz.simek.phm.dao;

import cz.simek.phm.model.Player;
import org.hibernate.Hibernate;

import java.util.List;

/**
 * Created by jenik on 23.2.17.
 */
public class PlayerDaoImpl extends AbstractDao<Integer, Player> implements PlayerDao {
    @Override
    public Player findById(Long id) {
        List<Player> players = getEntityManager().createQuery("SELECT p FROM Player p WHERE p.id=:id", Player.class).setParameter("id", id).getResultList();
        if (players.isEmpty()) {
            return null;
        } else if (players.size() > 1) {
            //TODO implemnent LOGGING!!!
            System.out.println("LOG more than one record found - possible data inconsistency!");
        }
        Hibernate.initialize(players.get(0).getAllTeamHistory());
        return players.get(0);
    }

    @Override
    public void saveNew(Player player) {
        persist(player);
    }

    @Override
    public void updatePlayer(Player player) {
        update(player);
    }

    @Override
    public void deletePlayer(Player player) {
        delete(player);
    }
}
