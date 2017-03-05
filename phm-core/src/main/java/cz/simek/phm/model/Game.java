package cz.simek.phm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jenik on 8.2.17.
 */
@Entity
@Table(name = "GAME")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SEASON"))
    private Season season;

    @Column(name = "date")
    private Date date;

    @OneToOne
    @JoinColumn(name = "homeTeam", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HOME_TEAM"))
    private Team homeTeam;

    @OneToOne
    @JoinColumn(name = "awayTeam", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_AWAY_TEAM"))
    private Team awayTeam;

    @Column(name = "homeScore")
    private int homeScore;

    @Column(name = "awayScore")
    private int awayScore;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GAME_PLAYER", joinColumns = {
            @JoinColumn(name = "game_id", foreignKey = @ForeignKey(name = "FK_GAME_PLAYER_ID"), nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "player_id", foreignKey = @ForeignKey(name = "FK_PLAYER_GAME_ID"),
                    nullable = false, updatable = false)})
    private List<Player> playerList;


//    public void removeTeamMember(Team team){
//        if ()
//    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
