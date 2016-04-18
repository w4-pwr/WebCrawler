package pwr.po.webcrawler.web.dto;

import lombok.Getter;
import lombok.Setter;
import pwr.po.webcrawler.model.Result;
import pwr.po.webcrawler.model.user.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Rafał Niedźwiecki on 17.04.2016.
 */
@Getter
@Setter
public class QueryDTO {
    private Long id;

    private long userId;

    private String keyword;

    private Date addedDate;

    private List<Result> result;
}
