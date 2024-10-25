// MyWebSocketHandler.java
package com.example.websocket_server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {
    private ObjectMapper objectMapper = new ObjectMapper();
    private String room;

    public MyWebSocketHandler(String room) {
        this.room = room;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        System.out.println("Received message from " + chatMessage.getUsername() + " in room " + room + ": " + chatMessage.getMessage());

        // Echo the received message back to the client
        session.sendMessage(new TextMessage("Server: " + chatMessage.getMessage()));
    }
}