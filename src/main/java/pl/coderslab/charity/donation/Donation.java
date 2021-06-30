package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class Donation {
    private int quantity;
    private Set<Category> categories;
    private Institution institution;
    private String street;
    private String phone;
    private String city;
    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;

}
