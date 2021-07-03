package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.charity.user.repository.entity.RoleEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    @NotEmpty(message = "Please enter an email")
    private String email;

    @Size(min = 8, message = "Password couldn't be empty and should have at least 8 letters")
    private String password;

    private int enabled;

    private Set<RoleEntity> roleEntities;

}
