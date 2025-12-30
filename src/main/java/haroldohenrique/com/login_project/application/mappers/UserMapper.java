package haroldohenrique.com.login_project.application.mappers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import haroldohenrique.com.login_project.application.dtos.UserCreateDTO;
import haroldohenrique.com.login_project.application.dtos.UserResponseDTO;
import haroldohenrique.com.login_project.domain.entities.UserEntity;

@Component
public class UserMapper {
    private final EnderecoMapper enderecoMapper;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(EnderecoMapper enderecoMapper, PasswordEncoder passwordEncoder) {
        this.enderecoMapper = enderecoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity toEntity(UserCreateDTO dto) {
        if (dto == null)
            return null;
        
        var password = passwordEncoder.encode(dto.getPassword());

        return UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .telephone(dto.getTelephone())
                .password(password)
                .endereco(enderecoMapper.toEndereco(dto.getEndereco()))
                .build();
    }

    public UserResponseDTO toDTO(UserEntity user) {
        if(user == null) return null;
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .telephone(user.getTelephone())
                .endereco(enderecoMapper.toDTO(user.getEndereco()))
                .build();
    }
}
