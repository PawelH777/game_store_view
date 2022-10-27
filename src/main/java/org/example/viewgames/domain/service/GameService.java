package org.example.viewgames.domain.service;

import org.example.viewgames.domain.model.GameDO;

import java.util.Set;


public interface GameService {

    GameDO findById(long id);

    Set<GameDO> findAll();

    Set<GameDO> findByName(String name);

    Set<GameDO> findByGenre(String genre);

    Set<GameDO> findBySearchText(String searchText);
}
