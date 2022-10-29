package org.example.viewgames.infrastructure.repository;

import org.example.viewgames.domain.model.GameDO;
import org.example.viewgames.domain.model.GameStatus;
import org.example.viewgames.infrastructure.model.Game;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameRepositoryAdapterImpl implements GameRepositoryAdapter {

    private final GameRepository gameRepository;

    public GameRepositoryAdapterImpl(final GameRepository crudRepository) {
        this.gameRepository = crudRepository;
    }

    @Override
    public GameDO findById(final long id) {
        final Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isEmpty()) {
            return null;
        }

        return buildGameDO(gameOptional.get());
    }

    @Override
    public Set<GameDO> findAll() {
        return buildGameDoSet(gameRepository.findAll());
    }

    @Override
    public Set<GameDO> findByName(final String name) {
        return buildGameDoSet(gameRepository.findByNameContainingAndGameStatusAllIgnoreCase(name, GameStatus.PUBLISHED));
    }

    @Override
    public Set<GameDO> findByGenre(final String genre) {
        return buildGameDoSet(gameRepository.findByGenreContainingAndGameStatusAllIgnoreCase(genre, GameStatus.PUBLISHED));
    }

    @Override
    public Set<GameDO> findBySearchText(final String searchText) {
        return buildGameDoSet(gameRepository.findByNameContainingAndGenreContainingAndGameStatusAllIgnoreCase(searchText, searchText, GameStatus.PUBLISHED));
    }

    private GameDO buildGameDO(final Game game) {
        return GameDO.builder()
                .id(game.getId())
                .name(game.getName())
                .genre(game.getGenre())
                .price(game.getPrice())
                .build();
    }

    private Set<GameDO> buildGameDoSet(final Iterable<Game> games) {
        final Set<GameDO> gameDOs = new HashSet<>();
        for(final Game game : games) {
            gameDOs.add(buildGameDO(game));
        }
        return gameDOs;
    }
}
