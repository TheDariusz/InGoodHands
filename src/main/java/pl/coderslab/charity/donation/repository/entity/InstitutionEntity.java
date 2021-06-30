package pl.coderslab.charity.donation.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import pl.coderslab.charity.donation.Donation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "institution")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InstitutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "institution")
    private Set<DonationEntity> donations;

    public InstitutionEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public InstitutionEntity(String name, String description, Set<DonationEntity> donations) {
        this.name=name;
        this.description=description;
        this.donations=donations;
    }

    public void addDonationEntity(DonationEntity donationEntity) {
        this.donations.add(donationEntity);
        donationEntity.setInstitution(this);
    }
}
