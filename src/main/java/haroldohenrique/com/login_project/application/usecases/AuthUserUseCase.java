package haroldohenrique.com.login_project.application.usecases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import haroldohenrique.com.login_project.application.dtos.AuthUserRequestDTO;
import haroldohenrique.com.login_project.application.dtos.AuthUserResponseDTO;
import haroldohenrique.com.login_project.domain.exceptions.InvalidCredentialsException;
import haroldohenrique.com.login_project.domain.repositories.UserRepository;

@Service
public class AuthUserUseCase {

    private final String jwtSecret;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthUserUseCase(@Value("${JWT_SECRET}") String jwtSecret, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.jwtSecret = jwtSecret;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUserResponseDTO execute(AuthUserRequestDTO authUserRequestDTO) throws InvalidCredentialsException {
        var user = userRepository.findByEmail(authUserRequestDTO.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Email ou senha incorretos"));

        var passwordMatches = passwordEncoder.matches(authUserRequestDTO.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new InvalidCredentialsException("Email ou senha incorretos");
        }

        var roles = Arrays.asList("USER");

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("login_project_user")
                .withClaim("roles", roles)
                .withSubject(user.getId().toString())
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthUserResponseDTO.builder()
                .access_token(token)
                .expiresIn(expiresIn.toEpochMilli())
                .roles(roles)
                .build();

    }
}
