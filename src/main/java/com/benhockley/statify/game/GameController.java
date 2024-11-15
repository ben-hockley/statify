package com.benhockley.statify.game;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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

    //post
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    void postNewGame(@Valid @RequestBody Game game){
        gameRepository.create(game);
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
