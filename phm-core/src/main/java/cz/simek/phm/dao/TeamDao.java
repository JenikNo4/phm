package cz.simek.phm.dao;

import cz.simek.phm.model.Team;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jenik on 31.1.17.
 */
@Transactional
public interface TeamDao {

    Team findById(Long id);

    List<Team> findByNameWithLike(String partName);

    void saveNew(Team team);

    void updateTeam(Team team);

    void deleteTeam(Team team);

}
