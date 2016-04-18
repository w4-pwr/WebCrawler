package pwr.po.webcrawler.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;
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
        User user = new User();
        user.setId(queryDTO.getUserId());
        query.setUser(user);
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
        queryDTO.setUserId(query.getUser().getId());
        queryDTO.setResult(query.getResult());
        return queryDTO;
    }

}
