package haroldohenrique.com.login_project.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import haroldohenrique.com.login_project.domain.entities.UserEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByTelephone(String telephone);

    Optional<UserEntity> findByEmail(String email);
    
}
