package org.grizz.mirko.task;

import lombok.extern.slf4j.Slf4j;
import org.grizz.mirko.model.PlayerResponse;
import org.grizz.mirko.model.repository.PlayerResponseRepository;
import org.grizz.mirko.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MirkoPosterTask implements Runnable {
    @Autowired
    private PlayerResponseRepository responseRepository;
    @Autowired
    private Sender sender;

    public void run() {
        List<PlayerResponse> responses = getResponses();
        send(responses);
        save(responses);
    }

    private List<PlayerResponse> getResponses() {
        log.info("Getting responses");
        return responseRepository.findBySentOrderByTimestampAsc(false);
    }

    private void send(List<PlayerResponse> responses) {
        log.info("Sending responses");
        responses.forEach(sender::send);
    }

    private void save(List<PlayerResponse> responses) {
        log.info("Saving responses");
        responseRepository.save(responses);
    }
}
