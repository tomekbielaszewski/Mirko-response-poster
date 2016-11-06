package org.grizz.mirko.service;

import com.crozin.wykop.sdk.Command;
import com.google.gson.Gson;
import org.grizz.mirko.model.MirkoPostResponse;
import org.grizz.mirko.model.PlayerResponse;
import org.grizz.mirko.sesssion.RefreshableSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private RefreshableSession session;

    public void send(PlayerResponse playerResponse) {
        MirkoPostResponse mirkoPostResponse = sendPlayerResponse(playerResponse);
        playerResponse.setSent(mirkoPostResponse.isSuccess());
    }

    private MirkoPostResponse sendPlayerResponse(PlayerResponse playerResponse) {
        Command sendCommand = getPMSendCommand(playerResponse.getReceiver(), playerResponse.getResponse());
        String execute = session.execute(sendCommand);
        Gson gson = new Gson();
        return gson.fromJson(execute, MirkoPostResponse.class);
    }

    private Command getPMSendCommand(String receiver, String response) {
        Command command = new Command("pm", "sendMessage", receiver);
        command.addPostParameter("body", response);
        return command;
    }
}
