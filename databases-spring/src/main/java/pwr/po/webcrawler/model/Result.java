package pwr.po.webcrawler.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Rafal Pieniazek on 2016-04-15.
 * Entiy represents results found for {Query}
 */
@Entity
@Getter
@Setter
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue
    private Long id ;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "query_id")
    private Query query;

    private String resultUrl;

    private Date foundDate;
}
