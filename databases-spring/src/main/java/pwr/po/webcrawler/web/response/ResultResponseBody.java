package pwr.po.webcrawler.web.response;

import lombok.Getter;
import lombok.Setter;
import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.web.dto.QueryDTO;
import pwr.po.webcrawler.web.dto.ResultDTO;
import pwr.po.webcrawler.web.mapper.QueryMapper;
import pwr.po.webcrawler.web.mapper.ResultsMapper;

import java.util.List;

/**
 * Created by Rafał Pieniążek on 02.06.2016.
 */
@Getter
@Setter
public class ResultResponseBody {

    QueryDTO search;
    List<ResultDTO> results;

    public ResultResponseBody(Query query){
        search = QueryMapper.map(query);
        results = ResultsMapper.mapDTOList(query);
    }
}
