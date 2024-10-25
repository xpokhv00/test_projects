// `WebSocketClientService.java`
package com.example.websocket_client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.net.URI;

@Service
public class WebSocketClientService {

    private final StandardWebSocketClient webSocketClient;
    private WebSocketSession session;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WebSocketClientService(StandardWebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    public void connect(String room) {
        try {
            session = webSocketClient
                    .doHandshake(new MyWebSocketHandler(), new WebSocketHttpHeaders(), URI.create("ws://localhost:8080/ws/" + room))
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(ChatMessage chatMessage) throws Exception {
        if (session != null && session.isOpen()) {
            String message = objectMapper.writeValueAsString(chatMessage);
            session.sendMessage(new TextMessage(message));
        }
    }

    private static class MyWebSocketHandler extends AbstractWebSocketHandler {
        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            System.out.println("Received from WebSocket server: " + message.getPayload());
        }
    }
}