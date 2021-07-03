package pl.coderslab.charity.user;


import pl.coderslab.charity.user.repository.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> findByUserEmail(String email);

    void saveUser(UserEntity userEntity);

    boolean existUserDto(UserDto userDto);

    void saveUserDto(UserDto userDto);
}
