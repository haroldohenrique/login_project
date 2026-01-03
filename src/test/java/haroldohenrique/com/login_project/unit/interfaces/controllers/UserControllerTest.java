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
import org.springframework.security.core.Authentication;

import haroldohenrique.com.login_project.application.dtos.UserCreateDTO;
import haroldohenrique.com.login_project.application.dtos.UserResponseDTO;
import haroldohenrique.com.login_project.application.usecases.CreateUserUseCase;
import haroldohenrique.com.login_project.application.usecases.ReadUserUseCase;
import haroldohenrique.com.login_project.interfaces.controllers.UserController;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private ReadUserUseCase readUserUseCase;

    @Mock
    private Authentication authentication;

    @Test
    @DisplayName("deve ser possível registrar um usuário")
    void should_be_able_to_register_a_user() {
        UserCreateDTO dto = new UserCreateDTO();
        UserResponseDTO responseDTO = new UserResponseDTO();

        when(createUserUseCase.execute(dto)).thenReturn(responseDTO);

        ResponseEntity<UserResponseDTO> result = userController.register(dto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(responseDTO, result.getBody());

        verify(createUserUseCase).execute(dto);

    }

    @Test
    @DisplayName("deve ser possível retornar um usuário")
    void should_be_able_to_return_a_user(){
        String userId = "id-123";
        UserResponseDTO responseDTO = new UserResponseDTO();

        when(authentication.getPrincipal()).thenReturn(userId);
        when(readUserUseCase.execute(userId)).thenReturn(responseDTO);

        ResponseEntity<UserResponseDTO> result =
                userController.readUser(authentication);

        
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseDTO, result.getBody());

        verify(readUserUseCase).execute(userId);

    }
}
