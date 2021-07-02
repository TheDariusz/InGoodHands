package pl.coderslab.charity.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.user.repository.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
