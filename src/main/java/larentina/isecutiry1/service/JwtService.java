package larentina.isecutiry1.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.nio.charset.StandardCharsets;


@Service
public class JwtService {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            "mySecretKey123456789012345678901234567890".getBytes(StandardCharsets.UTF_8)
    );
    private final long expirationMillis = 24 * 60 * 60 * 1000; // 1 день

    // Генерация JWT по username
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey) // HS256 берётся автоматически из типа ключа
                .compact();
    }

    // Валидация токена и извлечение username
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(token)
                .getBody()
                .getSubject();
    }
}
