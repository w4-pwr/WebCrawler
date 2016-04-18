package pwr.po.webcrawler.model;

import lombok.Getter;
import lombok.Setter;
import pwr.po.webcrawler.model.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    private String keyword;

    private Date addedDate;

    @OneToMany(mappedBy = "query",cascade = CascadeType.ALL)
    private List<Result> result;
}
