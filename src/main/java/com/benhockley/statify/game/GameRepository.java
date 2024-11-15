package com.benhockley.statify.game;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {

    private static final Logger log = LoggerFactory.getLogger(GameRepository.class);
    private final JdbcClient jdbcClient;

    public GameRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Game> findAll() {
        return jdbcClient.sql("SELECT * FROM game")
                .query(Game.class)
                .list();
    }

    public Optional<Game> findById(Integer id) {
        return jdbcClient.sql("SELECT id,home_or_away,opponent,date,points_for,points_against FROM Game WHERE id = :id" )
                .param("id", id)
                .query(Game.class)
                .optional();
    }

    public void create(Game newGame) {
        var updated = jdbcClient.sql("INSERT INTO Game(id, home_or_away,opponent,date,points_for,points_against) values(?,?,?,?,?,?)")
                .params(List.of(newGame.id(), newGame.homeOrAway().toString(),newGame.opponent(),newGame.date(),newGame.pointsFor(),newGame.pointsAgainst()))
                .update();

        Assert.state(updated == 1, "Failed to create new game against " + newGame.opponent());
    }

    public void update(Game game, Integer id) {
        var updated = jdbcClient.sql("update game set home_or_away = ?, opponent = ?, date = ?, points_for = ?, points_against = ? where id = ?")
                .params(List.of(game.homeOrAway().toString(),game.opponent(),game.date(),game.pointsFor(),game.pointsAgainst(), id))
                .update();

        Assert.state(updated == 1, "Failed to update game with id: " + game.id());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from game where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete game with id: " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from game").query().listOfRows().size();
    }

    public void saveAll(List<Game> games) {
        games.stream().forEach(this::create);
    }
}
