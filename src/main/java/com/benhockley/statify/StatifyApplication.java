package com.benhockley.statify;

import com.benhockley.statify.game.Game;
import com.benhockley.statify.game.GameRepository;
import com.benhockley.statify.game.HomeOrAway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class StatifyApplication {
	private static final Logger log = LoggerFactory.getLogger(StatifyApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(StatifyApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(GameRepository gameRepository) {
		return args -> {
			Game game = new Game(99, HomeOrAway.HOME, "Celtics", LocalDate.now(), 100, 95);
			gameRepository.create(game);
		};
	}
}

