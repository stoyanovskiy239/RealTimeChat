package chat.controller;

import chat.model.ChatMessage;
import chat.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {
    private final MessageService myMessageService;

    public HistoryController(MessageService messageService) {
        this.myMessageService = messageService;
    }

    @GetMapping("/messages")
    public List<ChatMessage> getAllMessages() {
        return myMessageService.getHistory();
    }
}
