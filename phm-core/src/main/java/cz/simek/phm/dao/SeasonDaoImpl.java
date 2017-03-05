package cz.simek.phm.dao;

import cz.simek.phm.model.Season;
import org.springframework.stereotype.Repository;

/**
 * Created by jenik on 8.2.17.
 */
@Repository
public class SeasonDaoImpl extends AbstractDao<Integer, Season> implements SeasonDao {

    public Season findById(Long id) {
        Season season = (Season) getEntityManager().createQuery("SELECT s FROM Season s WHERE s.id=:id", Season.class).setParameter("id", id).getSingleResult();
        return season;
    }

    public Season findByNameAndYears(String name, String years){
        Season season = (Season) getEntityManager().createQuery("SELECT s FROM Season s WHERE s.name=:name AND s.years=:years" , Season.class).setParameter("name", name).setParameter("years",years).getSingleResult();
        return season;
    }

    public void saveNew(Season season) {
        persist(season);
    }
}
