package chat.service;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final Set<String> activeUsers = ConcurrentHashMap.newKeySet();

    public boolean addUser(String username) {
        return activeUsers.add(username);
    }

    public void removeUser(String username) {
        activeUsers.remove(username);
    }

    public boolean isUserActive(String username) {
        return activeUsers.contains(username);
    }
}
