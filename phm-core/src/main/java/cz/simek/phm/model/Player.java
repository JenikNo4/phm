package cz.simek.phm.model;

import cz.simek.phm.enums.Position;
import cz.simek.phm.enums.StickSide;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jenik on 23.2.17.
 */
@Entity
@Table(name = "PLAYER")
public class Player implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surename")
    private String surename;

    @Column(name = "jersey")
    private Integer jersey;

    @Column(name = "position")
    private Position position;

    @Column(name = "image")
    private String imagePath;

    @Column(name = "stick")
    private StickSide stick;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "birthplace")
    private String birthPlace;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "playerList")
    private List<Game> gameList;

    @ManyToOne
    @JoinColumn(name = "actualteam_id", nullable = true, foreignKey = @ForeignKey(name = "FK_ACTUAL_TEAM"))
    private Team actualTeam;

    @OneToMany(mappedBy = "player")
    @Cascade(CascadeType.DELETE)
    private List<SeasonTeamPlayer> allTeamHistory;


//    @ManyToMany
//    @JoinTable(name = "SEASON_TEAM_PLAYER", joinColumns = {
//            @JoinColumn(name = "season_team_player_id", foreignKey = @ForeignKey(name = "FK_SEASON_TEAM_PLAYER_ID"), nullable = false, updatable = false)},
//            inverseJoinColumns = {@JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "FK_TEAM_PLAYER_SEASON_ID"),
//                    nullable = false, updatable = false)})
//    @MapKeyJoinColumn(name = "season_id")
//    @ElementCollection
//    private Map<Season, Team> allTeams;

    //GETTERS AND SETTERS


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

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public Integer getJersey() {
        return jersey;
    }

    public void setJersey(Integer jersey) {
        this.jersey = jersey;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public StickSide getStick() {
        return stick;
    }

    public void setStick(StickSide stick) {
        this.stick = stick;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public Team getActualTeam() {
        return actualTeam;
    }

    public void setActualTeam(Team actualTeam) {
        this.actualTeam = actualTeam;
    }

    public List<SeasonTeamPlayer> getAllTeamHistory() {
        return allTeamHistory;
    }

    public void setAllTeamHistory(List<SeasonTeamPlayer> allTeamHistory) {
        this.allTeamHistory = allTeamHistory;
    }
}
