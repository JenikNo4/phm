package cz.simek.phm.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jenik on 2.3.17.
 */
@Entity
@Table(name = "SEASON_TEAM_PLAYER",
        uniqueConstraints = { @UniqueConstraint(name = "SeasonTeamPlayerUC",columnNames = { "season_id", "team_id", "player_id" } ) } )
public class SeasonTeamPlayer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SEASON_TEAM_PLAYER_SEASON"))
    private Season season;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SEASON_TEAM_PLAYER_TEAM"))
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SEASON_TEAM_PLAYER_PLAYER"))
    private Player player;

    //GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
