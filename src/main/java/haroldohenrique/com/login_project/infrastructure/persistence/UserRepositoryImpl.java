package haroldohenrique.com.login_project.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import haroldohenrique.com.login_project.domain.entities.UserEntity;
import haroldohenrique.com.login_project.domain.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(UUID id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByTelephone(String telephone) {
        return jpaUserRepository.existsByTelephone(telephone);
    }
}
