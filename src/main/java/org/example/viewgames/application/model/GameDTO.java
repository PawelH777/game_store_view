package org.example.viewgames.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GameDTO {

    private long id;

    private String name;

    private String genre;
}
