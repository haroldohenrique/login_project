package haroldohenrique.com.login_project.application.mappers;

import org.springframework.stereotype.Component;

import haroldohenrique.com.login_project.application.dtos.EnderecoDTO;
import haroldohenrique.com.login_project.domain.valueobjects.Endereco;

@Component
public class EnderecoMapper {

    public Endereco toEndereco(EnderecoDTO dto) {

        if (dto == null)
            return null;

        return Endereco.builder()
                .rua(dto.getRua())
                .numero(dto.getNumero())
                .bairro(dto.getBairro())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .cep(dto.getCep())
                .complemento(dto.getComplemento())
                .build();
    }

    public EnderecoDTO toDTO(Endereco endereco) {
        if (endereco == null)
            return null;

        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .complemento(endereco.getComplemento())
                .build();
    }

//     Endereco endereco = enderecoMapper.toEndereco(userCreateDTO.getEndereco());
// userEntity.setEndereco(endereco);

}
