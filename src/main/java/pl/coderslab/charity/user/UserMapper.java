package pl.coderslab.charity.user;

import pl.coderslab.charity.user.repository.entity.UserEntity;

public class UserMapper {
    public UserEntity toEntity(UserDto userDto) {
        return new UserEntity(
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getEnabled(),
                userDto.getRoleEntities()
        );
    }

    public UserDto toUserDto(UserEntity user) {
        return new UserDto(
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                user.getRoleEntities()
        );
    }
}
