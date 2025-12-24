package haroldohenrique.com.login_project.application.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {

    @NotBlank(message = "O campo [name] é obrigatório")
    private String name;

    @NotBlank(message = "O campo [email] é obrigatório")
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @NotBlank(message = "O campo [telephone] é obrigatório")
    @Pattern(regexp = "^\\+55\\s?(\\d{2})\\s?9?\\d{4}-?\\d{4}$", message = "O campo [telephone] deve estar no formato +55 XX XXXXX-XXXX ou +55 XX XXXX-XXXX")
    private String telephone;

    @NotBlank(message = "O campo [password] é obrigatório")
    @Length(min = 6, max = 25, message = "O campo [password] deve conter entre 6 e 25 digitos")
    private String password;


    @Valid
    @NotNull(message = "O campo [endereco] é obrigatório")
    private EnderecoDTO endereco;
}
