package pl.coderslab.charity.user.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.charity.user.repository.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "verification_token", schema = "public")
public class VerificationTokenEntity {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

    @Column(name = "expiry_date")
    private Date expiryDate;

    public void setExpiryDate() {
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationTokenEntity(String token, UserEntity user, Date expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public VerificationTokenEntity(String token, UserEntity user) {
        this.token=token;
        this.user=user;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

}

