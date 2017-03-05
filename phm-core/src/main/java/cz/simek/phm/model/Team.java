package cz.simek.phm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jenik on 7.2.17.
 */
@Entity
@Table(name = "TEAM")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo")
    private String logo;

    @OneToMany(mappedBy = "homeTeam")
    private List<Game> gameListHomeTeam;

    @OneToMany(mappedBy = "awayTeam")
    private List<Game> gameListAwayTeam;

    @PreRemove
    private void removeTeamFromGames() {
        gameListAwayTeam.forEach(game -> game.setAwayTeam(null));
        gameListHomeTeam.forEach(game -> game.setHomeTeam(null));
    }

    // GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Game> getGameListHomeTeam() {
        return gameListHomeTeam;
    }

    public void setGameListHomeTeam(List<Game> gameListHomeTeam) {
        this.gameListHomeTeam = gameListHomeTeam;
    }

    public List<Game> getGameListAwayTeam() {
        return gameListAwayTeam;
    }

    public void setGameListAwayTeam(List<Game> gameListAwayTeam) {
        this.gameListAwayTeam = gameListAwayTeam;
    }
}
