package chat.controller;

import chat.model.ChatMessage;
import chat.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final MessageService myMessageService;

    public ChatController(MessageService messageService) {
        myMessageService = messageService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage message) {
        myMessageService.addMessage(message);
        return message;
    }
}
