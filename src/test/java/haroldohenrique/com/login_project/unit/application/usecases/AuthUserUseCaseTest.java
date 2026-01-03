package haroldohenrique.com.login_project.unit.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import haroldohenrique.com.login_project.application.dtos.AuthUserRequestDTO;
import haroldohenrique.com.login_project.application.dtos.AuthUserResponseDTO;
import haroldohenrique.com.login_project.application.usecases.AuthUserUseCase;
import haroldohenrique.com.login_project.domain.entities.UserEntity;
import haroldohenrique.com.login_project.domain.exceptions.InvalidCredentialsException;
import haroldohenrique.com.login_project.domain.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthUserUseCaseTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private AuthUserUseCase useCase;

    @Test
    @DisplayName("deve ser possível autenticar um usuário")
    void should_be_able_to_auth_a_user() {

        String jwtSecret = "test-secret";
        useCase = new AuthUserUseCase(jwtSecret, repository, encoder);

        var requestDTO = new AuthUserRequestDTO();
        requestDTO.setEmail("haroldo@gmail.com");
        requestDTO.setPassword("123456789");

        var entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setPassword("encoded-password");

        when(repository.findByEmail(requestDTO.getEmail())).thenReturn(Optional.of(entity));
        when(encoder.matches(requestDTO.getPassword(), entity.getPassword())).thenReturn(true);

        AuthUserResponseDTO response = useCase.execute(requestDTO);

        assertNotNull(response);
        assertNotNull(response.getAccess_token());
        assertNotNull(response.getExpiresIn());
        assertEquals(1, response.getRoles().size());

        verify(repository).findByEmail(requestDTO.getEmail());
        verify(encoder).matches(requestDTO.getPassword(), entity.getPassword());
    }

    @Test
    @DisplayName("deve lançar uma exceção quando o email não existir")
    void should_throw_exception_when_email_not_exists() {
        String jwtSecret = "test-secret";
        useCase = new AuthUserUseCase(jwtSecret, repository, encoder);

        var requestDTO = new AuthUserRequestDTO();
        requestDTO.setEmail("haroldo@gmail.com");
        requestDTO.setPassword("123456789");

        when(repository.findByEmail(requestDTO.getEmail())).thenReturn(Optional.empty());

        assertThrows(
                InvalidCredentialsException.class,
                () -> useCase.execute(requestDTO));

        verify(repository).findByEmail(requestDTO.getEmail());
        verify(encoder, never()).matches(any(), any());
    }

    @Test
    @DisplayName("deve lançar uma exceção quando as senhas não forem iguais")
    void should_throw_exception_when_password_does_not_match() {
        String jwtSecret = "test-secret";
        useCase = new AuthUserUseCase(jwtSecret, repository, encoder);

        var requestDTO = new AuthUserRequestDTO();
        requestDTO.setEmail("haroldo@gmail.com");
        requestDTO.setPassword("encoded-password");

        var entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setPassword("123456789");

        when(repository.findByEmail(requestDTO.getEmail())).thenReturn(Optional.of(entity));
        when(encoder.matches(requestDTO.getPassword(), entity.getPassword())).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> useCase.execute(requestDTO));

        verify(repository).findByEmail(requestDTO.getEmail());
        verify(encoder).matches(requestDTO.getPassword(), entity.getPassword());

    }
}
