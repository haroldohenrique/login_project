package haroldohenrique.com.login_project.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import haroldohenrique.com.login_project.application.dtos.AuthUserRequestDTO;
import haroldohenrique.com.login_project.application.dtos.AuthUserResponseDTO;
import haroldohenrique.com.login_project.application.usecases.AuthUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Autenticar - Users", description = "Autenticação do usuário")
public class AuthUserController {

    private final AuthUserUseCase authUserUseCase;

    @PostMapping("/login")
    @Operation(summary = "Autentica usuário", description = "Essa função faz o login do usuário no sistema e retorna um token autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthUserResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Não autenticado (token ausente/inválido)")
    })
    public ResponseEntity<AuthUserResponseDTO> login(@RequestBody AuthUserRequestDTO dto) {
        var token = authUserUseCase.execute(dto);
        return ResponseEntity.ok(token);
    }
}
