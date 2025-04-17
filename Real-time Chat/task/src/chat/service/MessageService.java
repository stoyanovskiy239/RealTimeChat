package chat.service;

import chat.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MessageService {
    private final List<ChatMessage> history = Collections.synchronizedList(new ArrayList<>());

    public void addMessage(ChatMessage msg) {
        history.add(msg);
    }

    public List<ChatMessage> getHistory() {
        return new ArrayList<>(history);
    }
}
