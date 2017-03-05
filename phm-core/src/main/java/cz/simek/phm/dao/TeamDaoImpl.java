package cz.simek.phm.dao;

import cz.simek.phm.model.Team;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jenik on 8.2.17.
 */
@Repository
@Transactional
public class TeamDaoImpl extends AbstractDao<Integer, Team> implements TeamDao {

    public Team findById(Long id) {
        List<Team> teamList = getEntityManager().createQuery("SELECT t FROM Team t WHERE t.id=:id", Team.class).setParameter("id", id).getResultList();
        if (teamList.isEmpty()) {
            return null;
        } else if (teamList.size() > 1) {
            //TODO implemnent LOGGING!!!
            System.out.println("LOG more than one record found - possible data inconsistency!");
        }
        Team team = teamList.get(0);
        Hibernate.initialize(team.getGameListAwayTeam());
        Hibernate.initialize(team.getGameListHomeTeam());
        return team;
    }

    public List<Team> findByNameWithLike(String partName) {
        List<Team> teamList = getEntityManager().createQuery("SELECT t FROM Team t WHERE t.name LIKE :name", Team.class).setParameter("name", "%" + partName + "%").getResultList();
        return teamList;
    }

    public void saveNew(Team team) {
        persist(team);
    }

    public void updateTeam(Team team) {
        update(team);
    }

    public void deleteTeam(Team team) {
        delete(team);
    }
}
