package pwr.po.webcrawler.web.mapper;

import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.web.dto.QueryDTO;

/**
 * Created by Rafał Niedźwiecki on 17.04.2016.
 */
public class QueryMapper {
    static public Query map(QueryDTO queryDTO)
    {
        if(queryDTO==null)
            return null;
        Query query = new Query();
        query.setId(queryDTO.getId());
        query.setAddedDate(queryDTO.getAddedDate());
        query.setKeyword(queryDTO.getKeyword());
        query.setResult(queryDTO.getResult());
        query.setUser(queryDTO.getUser());
        return query;
    }
    static public QueryDTO map (Query query)
    {
        if(query==null)
            return null;
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setId(query.getId());
        queryDTO.setAddedDate(query.getAddedDate());
        queryDTO.setKeyword(query.getKeyword());
        queryDTO.setUser(query.getUser());
        queryDTO.setResult(query.getResult());
        return queryDTO;
    }

}
