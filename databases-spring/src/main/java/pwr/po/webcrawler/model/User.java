package pwr.po.webcrawler.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private String firstName;

    private String lastName;

    private String profileImage;

    private String email;

    private String password;

    private Date registrationDate;

    @OneToOne
    @JoinColumn(name="preferences_id")
    private Preferences preferences;

    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long id ,String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }


}
