package pl.coderslab.charity.donation.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity(name = "donation")
@NoArgsConstructor
@Getter
@Setter
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int quantity;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<CategoryEntity> categories;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "institution_id", nullable = false)
    private InstitutionEntity institution;

    private String street;
    private String city;
    private String phone;
    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;

    public DonationEntity(int quantity, Set<CategoryEntity> categories, InstitutionEntity institution, String street, String city, String phone, String zipCode, LocalDate pickUpDate, LocalTime pickUpTime, String pickUpComment) {
        this.quantity = quantity;
        this.categories = categories;
        this.institution = institution;
        this.street = street;
        this.city = city;
        this.phone = phone;
        this.zipCode = zipCode;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.pickUpComment = pickUpComment;
    }
}
