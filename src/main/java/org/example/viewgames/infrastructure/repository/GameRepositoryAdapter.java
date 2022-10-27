package org.example.viewgames.infrastructure.repository;

import org.example.viewgames.domain.model.GameDO;

import java.util.Set;

public interface GameRepositoryAdapter {

    GameDO findById(long id);

    Set<GameDO> findAll();

    Set<GameDO> findByName(final String name);

    Set<GameDO> findByGenre(final String genre);

    Set<GameDO> findBySearchText(final String searchText);

}
