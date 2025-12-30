package haroldohenrique.com.login_project.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import haroldohenrique.com.login_project.application.dtos.UserResponseDTO;
import haroldohenrique.com.login_project.application.mappers.UserMapper;
import haroldohenrique.com.login_project.domain.exceptions.NotFoundException;
import haroldohenrique.com.login_project.domain.repositories.UserRepository;

@Service
public class ReadUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ReadUserUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO execute(String id) {
        UUID userId;
        try {
            userId = UUID.fromString(id);

        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Usuário não encontrado");
        }

        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        return userMapper.toDTO(user);

    }
}
