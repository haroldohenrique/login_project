package haroldohenrique.com.login_project.application.mappers;

import org.springframework.stereotype.Component;

import haroldohenrique.com.login_project.application.dtos.UserCreateDTO;
import haroldohenrique.com.login_project.application.dtos.UserResponseDTO;
import haroldohenrique.com.login_project.domain.entities.UserEntity;

@Component
public class UserMapper {
    private final EnderecoMapper enderecoMapper;

    public UserMapper(EnderecoMapper enderecoMapper) {
        this.enderecoMapper = enderecoMapper;
    }

    public UserEntity toEntity(UserCreateDTO dto) {
        if (dto == null)
            return null;

        return UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .telephone(dto.getTelephone())
                .password(dto.getPassword())
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
