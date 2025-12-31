package haroldohenrique.com.login_project.interfaces.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import haroldohenrique.com.login_project.application.dtos.UserCreateDTO;
import haroldohenrique.com.login_project.application.dtos.UserResponseDTO;
import haroldohenrique.com.login_project.application.usecases.CreateUserUseCase;
import haroldohenrique.com.login_project.application.usecases.ReadUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Informações do usuário")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final ReadUserUseCase readUserUseCase;

    @PostMapping("/register")
    @Operation(summary = "Criação de usuário no banco", description = "Essa função registra um usuário no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Duplicate resource.")
    })
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserUseCase.execute(dto));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Perfil do usuário logado", description = "Essa função mostra o perfil do usuário logado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Não autenticado (token ausente/inválido)"),
            @ApiResponse(responseCode = "403", description = "Sem permissão (role inválida)"),
            @ApiResponse(responseCode = "404", description = "User not found.")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<UserResponseDTO> readUser(Authentication auth) {
        var userId = (String) auth.getPrincipal();
        return ResponseEntity.ok(readUserUseCase.execute(userId));
    }
}
