package pl.coderslab.charity.donation;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Institution {

    private final String name;
    private final String description;
    private final Set<Donation> donations = new HashSet<>();

    public Institution(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
