package cz.simek.phm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jenik on 8.2.17.
 */
@Entity
@Table(name = "SEASON",
        uniqueConstraints = { @UniqueConstraint(name = "SeasonUC",columnNames = { "name", "years" } ) } )
public class Season implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "years", nullable = false, length = 9)
    private String years;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "season")
    private List<Game> gameList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
