package pl.coderslab.charity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;


public class UserDetailsSecurityService implements UserDetailsService {
    private UserService userService;

    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.findByUserEmail(email)
                .map(this::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    private UserDetails mapToUserDetails(UserDto userDto) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        userDto.getRoleEntities().forEach(
                role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()))
        );
        return new User(userDto.getEmail(), userDto.getPassword(), grantedAuthorities);
    }
}
