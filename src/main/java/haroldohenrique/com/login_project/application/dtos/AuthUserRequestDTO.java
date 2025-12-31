package haroldohenrique.com.login_project.application.dtos;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserRequestDTO {


    @Email(message = "O campo [email] deve conter um e-mail válido.")
    @Schema(example = "fulano@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Length(min = 6, max = 25, message = "O campo [password] deve conter entre 6 e 25 digitos")
    @Schema(example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
