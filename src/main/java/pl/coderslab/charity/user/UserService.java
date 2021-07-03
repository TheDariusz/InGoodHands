package pl.coderslab.charity.user;


import pl.coderslab.charity.user.repository.entity.UserEntity;
import pl.coderslab.charity.user.repository.entity.VerificationTokenEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> findByUserEmail(String email);

    UserEntity saveUser(UserEntity userEntity);

    boolean existUserDto(UserDto userDto);

    UserEntity saveUser(UserDto userDto);

    void createVerificationToken(UserEntity user, String token);

    VerificationTokenEntity getVerificationToken(String VerificationToken);
}
