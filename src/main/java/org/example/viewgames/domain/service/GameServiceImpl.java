package org.example.viewgames.domain.service;

import org.example.viewgames.domain.model.GameDO;
import org.example.viewgames.infrastructure.repository.GameRepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepositoryAdapter gameRepositoryAdapter;

    public GameServiceImpl(final GameRepositoryAdapter gameRepositoryAdapter) {
        this.gameRepositoryAdapter = gameRepositoryAdapter;
    }

    @Override
    public GameDO findById(final long id) {
        return gameRepositoryAdapter.findById(id);
    }

    @Override
    public Set<GameDO> findAll() {
        return gameRepositoryAdapter.findAll();
    }

    @Override
    public Set<GameDO> findByName(final String name) {
        return gameRepositoryAdapter.findByName(name);
    }

    @Override
    public Set<GameDO> findByGenre(final String genre) {
        return gameRepositoryAdapter.findByGenre(genre);
    }

    @Override
    public Set<GameDO> findBySearchText(final String searchText) {
        return gameRepositoryAdapter.findBySearchText(searchText);
    }
}
