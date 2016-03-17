package pwr.po.webcrawler.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "preferences")
public class Preferences {
    //TODO
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

}
