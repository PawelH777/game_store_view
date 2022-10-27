package org.example.viewgames.infrastructure.repository;

import org.example.viewgames.domain.model.GameStatus;
import org.example.viewgames.infrastructure.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    List<Game> findByNameContainingAndGameStatusAllIgnoreCase(String name, GameStatus gameStatus);

    List<Game> findByGenreContainingAndGameStatusAllIgnoreCase(String name, GameStatus gameStatus);

    //    @Query("SELECT g FROM Game g WHERE (g.name LIKE '%?1%' OR g.genre LIKE '%?1%') AND ")
    List<Game> findByNameContainingAndGenreContainingAndGameStatusAllIgnoreCase(String name, String genre, GameStatus gameStatus);
}
