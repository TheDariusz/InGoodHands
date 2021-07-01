package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

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

    public Donation toModel() {
        return new Donation(
                Integer.parseInt(this.getQuantity()),
                Set.copyOf(this.categories),
                new Institution(institution, null),
                this.street,
                this.phone,
                this.city,
                this.zipCode,
                LocalDate.parse(this.pickUpDate),
                LocalTime.parse(this.pickUpTime),
                this.pickUpComment
        );
    }
}

