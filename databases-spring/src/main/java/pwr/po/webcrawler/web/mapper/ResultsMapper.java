package pwr.po.webcrawler.web.mapper;

import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.model.Result;
import pwr.po.webcrawler.web.dto.ResultDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafał Pieniążek on 02.06.2016.
 */
public class ResultsMapper {

    public static ResultDTO map(Result result) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setId(result.getId());
        resultDTO.setRepeats(0);
        resultDTO.setUrl(result.getResultUrl());
        return  resultDTO;
    }

    public static List<ResultDTO> mapDTOList(Query query) {
        List<ResultDTO> results = new ArrayList<>();
        for(Result result: query.getResult()){
            results.add(map(result));
        }
        return results;
    }

}
