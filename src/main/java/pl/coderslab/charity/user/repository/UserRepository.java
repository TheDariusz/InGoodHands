package pl.coderslab.charity.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.user.repository.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
