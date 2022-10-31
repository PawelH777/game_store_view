package org.example.viewgames.infrastructure.listener;

import org.example.viewgames.infrastructure.model.EventType;
import org.example.viewgames.infrastructure.model.Game;
import org.example.viewgames.infrastructure.model.GameMessage;
import org.example.viewgames.infrastructure.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GameQueueListener {

    Logger logger = LoggerFactory.getLogger(GameQueueListener.class);

    private final GameRepository gameRepository;

    public GameQueueListener(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void processGameMessage(final GameMessage game) {
        logger.info("Start of game processing with Id " + game.id());
        if (game.eventType().equals(EventType.GAME_PUBLISHED) || game.eventType().equals(EventType.GAME_UNPUBLISHED)) {
            gameRepository.save(buildGame(game));
        } else if (game.eventType().equals(EventType.GAME_DELETED)) {
            gameRepository.deleteById(game.id());
        }
        logger.info("Game has been processed");
    }

    private Game buildGame(final GameMessage message) {
        return Game.builder()
                .id(message.id())
                .name(message.name())
                .genre(message.genre())
                .price(message.price())
                .gameStatus(message.status())
                .build();
    }
}
