package com.benhockley.statify.game;

import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/games") // all methods inside fall under this
public class GameController {

    private final GameRepository gameRepository;

    //dependency injection
    public GameController(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }


    @GetMapping("") // so this method is called when we hit /api/games
    public ModelAndView listAllGames(){

        List<Game> games = gameRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("yourGames"); // templates/yourGames.html
        modelAndView.addObject("gamesList", games);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getGame(@PathVariable Integer id){
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); // 404
        }
        ModelAndView modelAndView = new ModelAndView("gameDetails"); // templates/gameDetails.html
        modelAndView.addObject("game", game.get());
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView newGameForm() {
        ModelAndView modelAndView = new ModelAndView("newGame"); // templates/newGame.html
        modelAndView.addObject("game", new GameObject());
        return modelAndView; // templates/newGame.html
    }

    //post

    int activeId = 0; // increment this for each new game

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/new")
    public ModelAndView gameSubmit(@ModelAttribute GameObject game, Model model) {
        model.addAttribute("game", game);

        Random rand = new Random();
        gameRepository.create(new Game(activeId, HomeOrAway.HOME, game.getOpponent(), game.getDate(), game.getPointsFor(), game.getPointsAgainst()));

        activeId++;

        return listAllGames();
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @PutMapping("/{id}")
    void updateGame(@Valid @RequestBody Game game, @PathVariable Integer id){
        gameRepository.update(game, id);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    void deleteGame(@PathVariable Integer id){
        gameRepository.delete(id);
    }

}
