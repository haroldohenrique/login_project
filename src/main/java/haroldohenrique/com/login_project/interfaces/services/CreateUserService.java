package haroldohenrique.com.login_project.interfaces.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import haroldohenrique.com.login_project.application.dtos.UserCreateDTO;

@Service
public class CreateUserService {

    private final RestTemplate restTemplate;

    public CreateUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void execute(UserCreateDTO userCreateDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserCreateDTO> request = new HttpEntity<>(userCreateDTO, headers);

        String url = "http://localhost:8080/api/users/register";

        ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Erro ao criar usuário");
        }
    }
}