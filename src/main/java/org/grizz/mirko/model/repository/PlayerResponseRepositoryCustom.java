package org.grizz.mirko.model.repository;

import org.grizz.mirko.model.PlayerResponse;

import java.util.List;

public interface PlayerResponseRepositoryCustom {
    void update(PlayerResponse playerResponse);

    default void update(List<PlayerResponse> playerResponses) {
        playerResponses.forEach(this::update);
    }
}
