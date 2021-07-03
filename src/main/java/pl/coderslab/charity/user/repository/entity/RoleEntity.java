package pl.coderslab.charity.user.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;

}
