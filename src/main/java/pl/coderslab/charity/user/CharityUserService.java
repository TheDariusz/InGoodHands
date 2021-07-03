package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.charity.user.repository.RoleRepository;
import pl.coderslab.charity.user.repository.UserRepository;
import pl.coderslab.charity.user.repository.VerificationTokenRepository;
import pl.coderslab.charity.user.repository.entity.RoleEntity;
import pl.coderslab.charity.user.repository.entity.UserEntity;
import pl.coderslab.charity.user.repository.entity.VerificationTokenEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@RequiredArgsConstructor
public class CharityUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper userMapper;
    private final VerificationTokenRepository tokenRepository;

    @Override
    public Optional<UserDto> findByUserEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toUserDto);
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
//        userEntity.setEnabled(1);
        RoleEntity userRoleEntity = roleRepository.findByName("ROLE_USER");
        userEntity.setRoleEntities(new HashSet<>(Arrays.asList(userRoleEntity)));
        final UserEntity savedUserEntity = userRepository.save(userEntity);
        return savedUserEntity;
    }

    public boolean existUserDto(UserDto userDto) {
        return findByUserEmail(userDto.getEmail()).isPresent();
    }

    public UserEntity saveUser(UserDto userDto) {
        return saveUser(userMapper.toEntity(userDto));
    }

    @Override
    public VerificationTokenEntity getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    @Override
    public void createVerificationToken(UserEntity user, String token) {
        VerificationTokenEntity myToken = new VerificationTokenEntity(token, user);
        myToken.setExpiryDate();
        tokenRepository.save(myToken);
    }
}
