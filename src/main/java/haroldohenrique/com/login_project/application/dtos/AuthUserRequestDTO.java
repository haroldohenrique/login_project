package haroldohenrique.com.login_project.application.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserRequestDTO {

    //TODO aqui vai o swagger também

    // @Schema(example = "email@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email(message = "O campo [email] deve conter um e-mail válido.")
    private String email;

    // @Schema(example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    @Length(min = 6, max = 25, message = "O campo [password] deve conter entre 6 e 25 digitos")
    private String password;
}
