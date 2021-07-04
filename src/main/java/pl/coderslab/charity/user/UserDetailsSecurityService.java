package pl.coderslab.charity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.charity.user.repository.entity.UserEntity;

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
        final UserEntity user = userService.findByEmail(email);
        if (user==null) {
            throw new UsernameNotFoundException(email);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoleEntities().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true, true, true,
                grantedAuthorities);
    }

}
