package pwr.po.webcrawler.web.dto;

import lombok.Getter;
import lombok.Setter;
import pwr.po.webcrawler.model.Query;

/**
 * Created by Rafał Pieniążek on 02.06.2016.
 */
@Getter
@Setter
public class ResultDTO {
    Long id;
    String url;
    int repeats; //ile razy fraza powtórzyła się na tej stronie


}
