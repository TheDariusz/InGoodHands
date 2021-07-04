package pl.coderslab.charity.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.user.repository.entity.UserEntity;
import pl.coderslab.charity.user.repository.entity.VerificationTokenEntity;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {

    VerificationTokenEntity findByToken(String token);

    VerificationTokenEntity findByUser(UserEntity user);
}
