// WebSocketConfig.java
package com.example.websocket_server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("Registering WebSocket handlers");

        // Register handler for dynamic room endpoints
        String[] rooms = {"room1", "room2", "room3"}; // Example rooms, replace with dynamic logic if needed
        for (String room : rooms) {
            registry.addHandler(new MyWebSocketHandler(room), "/ws/" + room).setAllowedOrigins("*");
        }
    }
}