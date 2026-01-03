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

import haroldohenrique.com.login_project.application.dtos.EnderecoDTO;
import haroldohenrique.com.login_project.application.dtos.UserResponseDTO;
import haroldohenrique.com.login_project.application.mappers.UserMapper;
import haroldohenrique.com.login_project.application.usecases.ReadUserUseCase;
import haroldohenrique.com.login_project.domain.entities.UserEntity;
import haroldohenrique.com.login_project.domain.exceptions.NotFoundException;
import haroldohenrique.com.login_project.domain.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ReadUserUseCaseTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private ReadUserUseCase useCase;

    @Test
    @DisplayName("deve retornar um usuário")
    void should_return_user_dto() {
        UUID userId = UUID.randomUUID();
        String userIdAsString = userId.toString();

        var entity = new UserEntity();
        entity.setId(userId);

        var responseDTO = new UserResponseDTO();
        responseDTO.setEmail("haroldo@gmail.com");
        responseDTO.setId(userId);
        responseDTO.setName("haroldo henrique");
        responseDTO.setTelephone("4898314066");

        var endereco = new EnderecoDTO();
        endereco.setRua("Rua das Flores");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCep("01000-000");
        endereco.setComplemento("Apartamento 45");

        responseDTO.setEndereco(endereco);

        when(repository.findById(userId)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(responseDTO);

        var result = useCase.execute(userIdAsString);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        verify(repository).findById(userId);
        verify(mapper).toDTO(entity);

    }

    @Test
    @DisplayName("deve lançar uma exceção quando o usuário não existir")
    void should_throw_exception_when_user_not_exists() {

        UUID userId = UUID.randomUUID();
        String userIdAsString = userId.toString();

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> useCase.execute(userIdAsString));

        verify(repository).findById(userId);
        verify(mapper, never()).toDTO(any());

    }
}
