package com.example.lurenjiaspring.controller.websocket;

import com.example.lurenjiaspring.websocket.WebSocketServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PushController {

    @RequestMapping("/pushMessageToUser")
    public String pushMessageToUser(@RequestParam("userId") String userId,@RequestParam("message") String message) {
        try {
            WebSocketServer.sendMessageToUser(userId, message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Sucess";
    }
}
