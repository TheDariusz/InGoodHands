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

@RequiredArgsConstructor
public class CharityUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper userMapper;
    private final VerificationTokenRepository tokenRepository;

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity saveUser(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setEnabled(0);
        RoleEntity userRoleEntity = roleRepository.findByName("ROLE_USER");
        user.setRoleEntities(new HashSet<>(Arrays.asList(userRoleEntity)));
        return userRepository.save(user);
    }

    @Override
    public UserEntity saveRegisteredUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public boolean existUserDto(UserDto userDto) {
        return findByEmail(userDto.getEmail())!=null;
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
