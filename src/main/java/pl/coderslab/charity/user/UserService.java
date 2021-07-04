package pl.coderslab.charity.user;


import pl.coderslab.charity.user.repository.entity.UserEntity;
import pl.coderslab.charity.user.repository.entity.VerificationTokenEntity;

public interface UserService {

    UserEntity saveRegisteredUser(UserEntity userEntity);

    boolean existUserDto(UserDto userDto);

    UserEntity saveUser(UserDto userDto);

    void createVerificationToken(UserEntity user, String token);

    VerificationTokenEntity getVerificationToken(String verificationToken);

    UserEntity findByEmail(String email);
}
