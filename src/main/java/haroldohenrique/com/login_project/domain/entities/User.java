package haroldohenrique.com.login_project.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // entidade puro java, sem depender de JPA

    public User(UUID id, String name, String email, String password, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        changeName(name);
        changeEmail(email);
        changePassword(password);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void changeName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Nome obrigatório");
        this.name = name;
        touch();
    }

    public void changeEmail(String email) {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email obrigatório");
        this.email = email;
        touch();
    }

    public void changePassword(String password) {
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Senha obrigatória");
        this.password = password;
        touch();
    }

    private void touch() {
        this.updatedAt = LocalDateTime.now();
    }

    // getters

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}