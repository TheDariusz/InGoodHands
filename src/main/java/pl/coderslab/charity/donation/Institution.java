package pl.coderslab.charity.donation;

import lombok.Data;

import java.util.Set;

@Data
public class Institution {

    private final String name;
    private final String description;
    private final Set<Donation> donations;

}
