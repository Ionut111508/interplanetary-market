package ecommerce.interplanetary.controller;

import ecommerce.interplanetary.dto.LoginRequest;
import ecommerce.interplanetary.entity.Role;
import ecommerce.interplanetary.entity.User;
import ecommerce.interplanetary.repository.UserRepository;
import ecommerce.interplanetary.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElse(null);

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "roles", user.getRoles().stream().map(Role::getName).toList(),
                "token", token
        ));
    }
}
