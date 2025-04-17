package chat.listener;

import chat.service.UserService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    private final UserService myUserService;

    public WebSocketEventListener(UserService userService) {
        myUserService = userService;
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        String username = (String) StompHeaderAccessor.wrap(event.getMessage()).getSessionAttributes().get("username");
        if (username != null) {
            myUserService.removeUser(username);
        }
    }
}
