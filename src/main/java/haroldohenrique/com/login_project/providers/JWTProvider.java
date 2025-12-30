package haroldohenrique.com.login_project.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTProvider {

    private final String jwtSecret;

    public JWTProvider(@Value("${JWT_SECRET}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public DecodedJWT validateToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        token = token.replace("Bearer ", "").trim();

        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException ex) {
            return null;
        }
    }

}
