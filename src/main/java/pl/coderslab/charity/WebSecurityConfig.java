package pl.coderslab.charity;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.charity.mail.EmailService;
import pl.coderslab.charity.user.CharityUserService;
import pl.coderslab.charity.user.RegistrationListener;
import pl.coderslab.charity.user.UserDetailsSecurityService;
import pl.coderslab.charity.user.UserMapper;
import pl.coderslab.charity.user.UserService;
import pl.coderslab.charity.user.repository.RoleRepository;
import pl.coderslab.charity.user.repository.UserRepository;
import pl.coderslab.charity.user.repository.VerificationTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/charity", "/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                .antMatchers("/charity/register", "/charity/registrationConfirm", "/charity/badUser").permitAll()
                .antMatchers("/charity/login").permitAll()
                .antMatchers("/charity/form").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/charity/login").permitAll()
                .defaultSuccessUrl("/charity", true)
                .and()
                .logout()
                .logoutUrl("/charity/logout")
                .logoutSuccessUrl("/charity")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsSecurityService customUserDetailsService() {
        return new UserDetailsSecurityService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserService userService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder, VerificationTokenRepository tokenRepository) {
        return new CharityUserService(userRepository, roleRepository, encoder, new UserMapper(), tokenRepository);
    }

    @Bean
    public RegistrationListener registrationListener(UserService userService, MessageSource messageSource, EmailService emailService) {
        return new RegistrationListener(userService, messageSource, emailService);
    }


}
