package larentina.isecutiry1.service;

import larentina.isecutiry1.entity.AuthUser;
import larentina.isecutiry1.entity.Profile;
import larentina.isecutiry1.repository.AuthUserRepository;
import larentina.isecutiry1.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthUserRepository authUserRepo;
    private final ProfileRepository profileRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    // регистрация
    public void register(String username, String password, String fullName, String email) {
        if (authUserRepo.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        AuthUser user = AuthUser.builder()
                .username(username)
                .passwordHash(passwordEncoder.encode(password))
                .build();

        authUserRepo.save(user);

        Profile profile = Profile.builder()
                .authUser(user)
                .fullName(fullName)
                .email(email)
                .build();

        profileRepo.save(profile);
    }

    public String login(String username, String password) {
        AuthUser user = authUserRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(user.getUsername());
    }
}