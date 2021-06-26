package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DonationDto {
    private String quantity;
    private List<Category> categories;
    private String institution;
    private String street;
    private String phone;
    private String city;
    private String zipCode;
    private String pickUpDate;
    private String pickUpTime;
    private String pickUpComment;
}
