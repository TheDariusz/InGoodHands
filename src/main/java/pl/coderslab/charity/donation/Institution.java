package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Institution {

    private final String name;
    private final String description;
    private final Set<Donation> donations;

}
