package larentina.isecutiry1.controller;

import larentina.isecutiry1.dto.LoginRequestDto;
import larentina.isecutiry1.dto.RegisterRequestDto;
import larentina.isecutiry1.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto request) {
        authService.register(
                request.getUsername(),
                request.getPassword(),
                request.getFullName(),
                request.getEmail()
        );
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(token);
    }
}
