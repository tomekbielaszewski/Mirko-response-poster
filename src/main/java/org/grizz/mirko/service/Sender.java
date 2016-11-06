package org.grizz.mirko.service;

import com.crozin.wykop.sdk.Command;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.grizz.mirko.model.MirkoPostResponse;
import org.grizz.mirko.model.PlayerResponse;
import org.grizz.mirko.sesssion.RefreshableSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Sender {
    @Autowired
    private RefreshableSession session;

    public void send(PlayerResponse playerResponse) {
        boolean success = sendPlayerResponse(playerResponse);
        playerResponse.setSent(success);
    }

    private boolean sendPlayerResponse(PlayerResponse playerResponse) {
        log.info("Sending message to {}", playerResponse.getReceiver());
        Command sendCommand = getPMSendCommand(playerResponse.getReceiver(), playerResponse.getResponse());
        String mirkoResponse = session.execute(sendCommand);
        return mirkoResponse.equals("[true]");
    }

    private Command getPMSendCommand(String receiver, String response) {
        Command command = new Command("pm", "sendMessage", receiver);
        command.addPostParameter("body", response);
        return command;
    }
}
