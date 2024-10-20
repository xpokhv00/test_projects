package com.example.websocket_client;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.annotation.PostConstruct;
import java.net.URI;

@Service
public class WebSocketClientService {

    private final StandardWebSocketClient webSocketClient;
    private WebSocketSession session;

    public WebSocketClientService(StandardWebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    @PostConstruct
    public void connect() {
        try {
            session = webSocketClient
                    .doHandshake(new MyWebSocketHandler(), new WebSocketHttpHeaders(), URI.create("ws://localhost:8080/ws"))
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws Exception {
        if (session != null && session.isOpen()) {
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


