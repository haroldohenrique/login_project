package haroldohenrique.com.login_project.application.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String telephone;
    private EnderecoDTO endereco;
}
