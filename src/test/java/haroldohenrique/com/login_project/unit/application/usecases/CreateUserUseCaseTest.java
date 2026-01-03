package haroldohenrique.com.login_project.unit.application.usecases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import haroldohenrique.com.login_project.application.dtos.EnderecoDTO;
import haroldohenrique.com.login_project.application.dtos.UserCreateDTO;
import haroldohenrique.com.login_project.application.dtos.UserResponseDTO;
import haroldohenrique.com.login_project.application.mappers.UserMapper;
import haroldohenrique.com.login_project.application.usecases.CreateUserUseCase;
import haroldohenrique.com.login_project.domain.entities.UserEntity;
import haroldohenrique.com.login_project.domain.exceptions.DuplicateResourceException;
import haroldohenrique.com.login_project.domain.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private CreateUserUseCase useCase;

    @Test
    @DisplayName("deve criar um usuário com sucesso quando email e telefone não existem")
    void should_create_user_successfully() {

        var dto = new UserCreateDTO();
        dto.setEmail("haroldo@gmail.com");
        dto.setName("haroldo henrique");
        dto.setPassword("123456789");
        dto.setTelephone("48998314066");

        var endereco = new EnderecoDTO();
        endereco.setRua("Rua das Flores");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCep("01000-000");
        endereco.setComplemento("Apartamento 45");

        dto.setEndereco(endereco);

        var entity = new UserEntity();
        var responseDTO = new UserResponseDTO();

        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(false);

        when(userRepository.existsByTelephone(dto.getTelephone())).thenReturn(false);

        when(userMapper.toEntity(dto)).thenReturn(entity);

        when(userMapper.toDTO(entity)).thenReturn(responseDTO);

        // act
        var result = useCase.execute(dto);

        // assert
        assertNotNull(result);
        verify(userRepository).existsByEmail(dto.getEmail());
        verify(userRepository).existsByTelephone(dto.getTelephone());
        verify(userRepository).save(entity);
        verify(userMapper).toEntity(dto);
        verify(userMapper).toDTO(entity);
    }

    @Test
    @DisplayName("deve lançar exceção quando telefone já existir")
    void should_throw_exception_when_telephone_already_exists() {
        var dto = new UserCreateDTO();
        dto.setEmail("haroldo@email.com");
        dto.setTelephone("4899314066");

        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(userRepository.existsByTelephone(dto.getTelephone())).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> useCase.execute(dto));

        verify(userRepository).existsByEmail(dto.getEmail());
        verify(userRepository).existsByTelephone(dto.getTelephone());
        verify(userRepository, never()).save(any());
    }

    

    @Test
    @DisplayName("deve lançar exceção quando email já existir")
    void should_throw_exception_when_email_already_exists() {
        //o teste deve sempre respeitar a ordem correta do que eu estiver testando, por exemplo, não tem necessidade de eu testar o telefone sendo que ele já vai dar o DuplicateResourceException na verificação de email;
        var dto = new UserCreateDTO();
        dto.setEmail("haroldo@email.com");

        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> useCase.execute(dto));

        verify(userRepository).existsByEmail(dto.getEmail());
        verify(userRepository, never()).save(any());
    }

}
