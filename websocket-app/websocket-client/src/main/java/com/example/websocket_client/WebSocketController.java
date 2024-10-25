// `WebSocketController.java`
package com.example.websocket_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebSocketController {

    @Autowired
    private WebSocketClientService webSocketClientService;

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam String room, @RequestParam String message, Model model) {
        webSocketClientService.connect(room);
        try {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setUsername("user1"); // Set appropriate values
            chatMessage.setRole("user");
            chatMessage.setMessage(message);
            chatMessage.setGroup(room);

            webSocketClientService.sendMessage(chatMessage);
            model.addAttribute("messageSent", true);
            model.addAttribute("message", message);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("messageSent", false);
        }
        return "index";
    }
}