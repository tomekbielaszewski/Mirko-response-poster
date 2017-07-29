package org.grizz.mirko.model.repository.impl;

import org.grizz.mirko.model.PlayerResponse;
import org.grizz.mirko.model.repository.PlayerResponseRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class PlayerResponseRepositoryImpl implements PlayerResponseRepositoryCustom {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void update(PlayerResponse playerResponse) {
        Query query = new Query(new Criteria("id").is(playerResponse.getId()));

        Update update = new Update().set("sent", playerResponse.isSent());
        mongoOperations.updateFirst(query, update, PlayerResponse.class);
    }
}
