package com.benhockley.statify.game;

import java.time.LocalDate;

public class GameObject {
    private Integer id;
    private HomeOrAway homeOrAway;
    private String opponent;
    private LocalDate date;
    private Integer pointsFor;
    private Integer pointsAgainst;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HomeOrAway getHomeOrAway() {
        return homeOrAway;
    }

    public void setHomeOrAway(HomeOrAway homeOrAway) {
        this.homeOrAway = homeOrAway;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPointsFor() {
        return pointsFor;
    }

    public void setPointsFor(Integer pointsFor) {
        this.pointsFor = pointsFor;
    }

    public Integer getPointsAgainst() {
        return pointsAgainst;
    }

    public void setPointsAgainst(Integer pointsAgainst) {
        this.pointsAgainst = pointsAgainst;
    }
}
