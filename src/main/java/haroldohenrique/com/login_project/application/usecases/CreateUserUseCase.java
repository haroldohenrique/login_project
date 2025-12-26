package haroldohenrique.com.login_project.application.usecases;

import org.springframework.stereotype.Service;

import haroldohenrique.com.login_project.application.dtos.UserCreateDTO;
import haroldohenrique.com.login_project.application.dtos.UserResponseDTO;
import haroldohenrique.com.login_project.application.mappers.UserMapper;
import haroldohenrique.com.login_project.domain.exceptions.DuplicateResourceException;
import haroldohenrique.com.login_project.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class CreateUserUseCase {

    private final UserRepository repository;
    private final UserMapper mapper;

    public CreateUserUseCase(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public UserResponseDTO execute(UserCreateDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Já existe um usuário com esse email");
        }
        if (repository.existsByTelephone(dto.getTelephone())) {
            throw new DuplicateResourceException("Já existe um usuário com esse telefone");
        }

        var user = mapper.toEntity(dto);
        repository.save(user);
        return mapper.toDTO(user);

        
        
    }
}
