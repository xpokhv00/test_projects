package com.example.websocket_client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {

    private final WebSocketClientService webSocketClientService;

    public MessageController(WebSocketClientService webSocketClientService) {
        this.webSocketClientService = webSocketClientService;
    }

    @GetMapping("/")
    public String showMessageForm() {
        return "index";  // Render the index.html page
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam("message") String message, Model model) {
        try {
            webSocketClientService.sendMessage(message);
            model.addAttribute("messageSent", true);
            model.addAttribute("message", message);
        } catch (Exception e) {
            model.addAttribute("messageSent", false);
            model.addAttribute("error", "Error sending message: " + e.getMessage());
        }
        return "index";  // Render the same page with feedback
    }
}


