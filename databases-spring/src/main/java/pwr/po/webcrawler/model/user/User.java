package pwr.po.webcrawler.model.user;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pwr.po.webcrawler.model.Preferences;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "profile_image")
    private String profileImage;

    private String email;

    @Column(length = 60)
    private String password;

    @Column(columnDefinition = "default : \"ROLE_USER\"")
    private String[] role;

    @Column(name = "registration_date")
    private Date registrationDate;


    @OneToOne
    @JoinColumn(name = "preferences_id")
    private Preferences preferences;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }


    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long id, String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }
}
