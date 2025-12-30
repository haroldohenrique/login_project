package haroldohenrique.com.login_project.application.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserResponseDTO {
    private String access_token;
    private Long expiresIn;
    private List<String> roles;
}
