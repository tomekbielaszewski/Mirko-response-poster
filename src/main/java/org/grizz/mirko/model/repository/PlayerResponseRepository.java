package org.grizz.mirko.model.repository;

import org.grizz.mirko.model.PlayerResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayerResponseRepository extends MongoRepository<PlayerResponse, String> {
    List<PlayerResponse> findBySentOrderByTimestampAsc(boolean sent);
}
