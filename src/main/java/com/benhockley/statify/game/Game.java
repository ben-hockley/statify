package com.benhockley.statify.game;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record Game(Integer id,
                   HomeOrAway homeOrAway,
                   String opponent,
                   LocalDate date,
                   @PositiveOrZero Integer pointsFor,
                   @PositiveOrZero Integer pointsAgainst) {
}
