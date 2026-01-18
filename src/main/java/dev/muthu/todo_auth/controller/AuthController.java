package dev.muthu.todo_auth.controller;

import dev.muthu.todo_auth.model.User;
import dev.muthu.todo_auth.repository.UserRepository;
import dev.muthu.todo_auth.service.UserService;
import dev.muthu.todo_auth.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body){

        String email = body.get("email");
        String password = passwordEncoder.encode(body.get("password"));

        if(userRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>("Email already exists",HttpStatus.CONFLICT);
        }

        userService.createUser(User.builder().email(email).password(password).build());

        return new ResponseEntity<>("Registered Successfully", HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body){
        String email = body.get("email");
        String password = body.get("password");

        if(userRepository.findByEmail(email).isEmpty()){
            return new ResponseEntity<>("User not found!", HttpStatus.UNAUTHORIZED);
        }

        User user = userRepository.findByEmail(email).get();
        if(!passwordEncoder.matches(password, user.getPassword())){
            return new ResponseEntity<>("Invalid user!", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));

    }

}
