package haroldohenrique.com.login_project.unit.interfaces.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import haroldohenrique.com.login_project.application.dtos.AuthUserRequestDTO;
import haroldohenrique.com.login_project.application.dtos.AuthUserResponseDTO;
import haroldohenrique.com.login_project.application.usecases.AuthUserUseCase;
import haroldohenrique.com.login_project.interfaces.controllers.AuthUserController;

@ExtendWith(MockitoExtension.class)
public class AuthUserControllerTest {

    @InjectMocks
    private AuthUserController authUserController;

    @Mock
    private AuthUserUseCase useCase;

    @Test
    @DisplayName("deve ser possível autenticar um usuário")
    void should_be_able_to_auth_a_user() {
        AuthUserRequestDTO dto = new AuthUserRequestDTO();
        AuthUserResponseDTO response = new AuthUserResponseDTO();

        when(useCase.execute(dto)).thenReturn(response);

        ResponseEntity<AuthUserResponseDTO> result = authUserController.login(dto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(useCase).execute(dto);

    }
}
