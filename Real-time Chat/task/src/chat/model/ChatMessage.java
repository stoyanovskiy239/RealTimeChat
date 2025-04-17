package chat.model;

import java.time.LocalDateTime;

public class ChatMessage {
    private String mySender;
    private String myContent;
    private LocalDateTime myTimestamp;

    public ChatMessage() { }

    public ChatMessage(String sender, String content, LocalDateTime timestamp) {
        mySender = sender;
        myContent = content;
        myTimestamp = timestamp;
    }

    public String getSender() {
        return mySender;
    }

    public void setSender(String sender) {
        mySender = sender;
    }

    public String getContent() {
        return myContent;
    }

    public void setContent(String content) {
        myContent = content;
    }

    public LocalDateTime getTimestamp() {
        return myTimestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        myTimestamp = timestamp;
    }
}
