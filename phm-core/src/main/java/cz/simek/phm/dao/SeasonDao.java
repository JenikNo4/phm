package cz.simek.phm.dao;

import cz.simek.phm.model.Season;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jenik on 8.2.17.
 */
@Transactional
public interface SeasonDao {

    Season findById(Long id);

    Season findByNameAndYears(String name, String years);

    void saveNew(Season season);
}
