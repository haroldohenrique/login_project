package haroldohenrique.com.login_project.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import haroldohenrique.com.login_project.application.dtos.AuthUserRequestDTO;
import haroldohenrique.com.login_project.application.dtos.AuthUserResponseDTO;
import haroldohenrique.com.login_project.application.usecases.AuthUserUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserUseCase authUserUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthUserResponseDTO> login(@RequestBody AuthUserRequestDTO dto) {
        var token = authUserUseCase.execute(dto);
        return ResponseEntity.ok(token);
    }
}
