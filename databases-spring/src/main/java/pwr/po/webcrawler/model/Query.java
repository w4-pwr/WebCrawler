package pwr.po.webcrawler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import pwr.po.webcrawler.model.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Rafal Pieniazek on 2016-04-15.
 * Entity table represents query added by user
 */
@Entity
@Getter
@Setter
@Table(name ="queries")
public class Query {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    public User getUser()
    {
        return user;
    }

    private String keyword;

    private Date addedDate;

    @OneToMany(mappedBy = "query",cascade = CascadeType.ALL)
    private Set<Result> result;

    @JsonManagedReference
    public Set<Result> getResult()
    {
        return result;
    }
}

enum Status{
    PENDING, ACTIVE, COMPLETED
}


