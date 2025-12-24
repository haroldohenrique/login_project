package haroldohenrique.com.login_project.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import haroldohenrique.com.login_project.domain.entities.UserEntity;

public interface UserRepository {
    UserEntity save(UserEntity user);

    Optional<UserEntity> findById(UUID id);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAll();

    void deleteById(UUID id);

    boolean existsByEmail(String email);

    boolean existsByTelephone(String telephone);
}
