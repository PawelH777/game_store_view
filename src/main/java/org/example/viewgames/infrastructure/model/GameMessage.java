package org.example.viewgames.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.viewgames.domain.model.GameStatus;

import java.math.BigDecimal;

public record GameMessage(@JsonProperty("id") long id,
                          @JsonProperty("name") String name,
                          @JsonProperty("genre") String genre,
                          @JsonProperty("price") BigDecimal price,
                          @JsonProperty("status") GameStatus status) {

}
