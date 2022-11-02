package org.example.viewgames.application.controller;

import org.example.viewgames.application.model.GameDTO;
import org.example.viewgames.domain.model.GameDO;
import org.example.viewgames.domain.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService gameService;

    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{id}")
    ResponseEntity<GameDTO> findById(@PathVariable final long id) {
        return ResponseEntity.status(200)
                .body(buildGameDTO(gameService.findById(id)));
    }

    @GetMapping
    ResponseEntity<Set<GameDTO>> findAll() {
        return ResponseEntity.status(200)
                .body(buildGameDtoSet(gameService.findAll()));
    }

    @GetMapping("/name/{name}")
    ResponseEntity<Set<GameDTO>> findByName(@PathVariable final String name) {
        return ResponseEntity.status(200)
                .body(buildGameDtoSet(gameService.findByName(name)));
    }

    @GetMapping("/genre/{genre}")
    ResponseEntity<Set<GameDTO>> findByGenre(@PathVariable final String genre) {
        return ResponseEntity.status(200)
                .body(buildGameDtoSet(gameService.findByGenre(genre)));
    }

    @GetMapping("/search")
    ResponseEntity<Set<GameDTO>> findBySearchText(@RequestParam final String searchText) {
        return ResponseEntity.status(200)
                .body(buildGameDtoSet(gameService.findBySearchText(searchText)));
    }

    private GameDTO buildGameDTO(final GameDO game) {
        return GameDTO.builder()
                .id(game.getId())
                .name(game.getName())
                .genre(game.getGenre())
                .price(game.getPrice())
                .build();
    }

    private Set<GameDTO> buildGameDtoSet(final Iterable<GameDO> games) {
        final Set<GameDTO> gameDOs = new HashSet<>();
        for (final GameDO game : games) {
            gameDOs.add(buildGameDTO(game));
        }
        return gameDOs;
    }
}
