package haroldohenrique.com.login_project.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class EnderecoDTO {
    @NotBlank(message = "O campo [rua] é obrigatório")
    private String rua;

    @NotBlank(message = "O campo [numero] é obrigatório")
    private String numero;

    @NotBlank(message = "O campo [bairro] é obrigatório")
    private String bairro;

    @NotBlank(message = "O campo [cidade] é obrigatório")
    private String cidade;

    @NotBlank(message = "O campo [estado] é obrigatório")
    private String estado;

    @NotBlank(message = "O campo [cep] é obrigatório")
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "O campo [cep] deve estar no formato 00000-000")
    private String cep;

    private String complemento;
}
