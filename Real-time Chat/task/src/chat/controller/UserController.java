package chat.controller;

import chat.dto.UsernameRequest;
import chat.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@RestController
public class UserController {
    private final UserService myUserService;

    public UserController(UserService userService) {
        myUserService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UsernameRequest request) {
        String username = request.getUsername();
        if (!isNotBlank(username)) {
            return ResponseEntity.badRequest().body("Username cannot be empty.");
        }
        if (!username.matches("^[a-zA-Z0-9_]{3,15}$")) {
            return ResponseEntity.badRequest().body("Username must be 3-15 characters long and contain only letters, numbers, or underscores.");
        }
        if (myUserService.isUserActive(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken.");
        }
        myUserService.addUser(username);
        return ResponseEntity.ok("Username accepted.");
    }
}
