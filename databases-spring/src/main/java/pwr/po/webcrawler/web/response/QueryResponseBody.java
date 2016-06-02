package pwr.po.webcrawler.web.response;

import lombok.Getter;
import lombok.Setter;
import pwr.po.webcrawler.web.dto.QueryDTO;

import java.util.List;

/**
 * Created by Rafał Pieniążek on 02.06.2016.
 */
@Getter
@Setter
public class QueryResponseBody {
    List<QueryDTO> searches;
}
