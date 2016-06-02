package pwr.po.webcrawler.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;
import pwr.po.webcrawler.web.dto.QueryDTO;
import pwr.po.webcrawler.web.response.QueryResponseBody;

import java.util.ArrayList;
import java.util.List;


public class QueryMapper {


    static public Query map(QueryDTO queryDTO) {
        if (queryDTO == null)
            return null;
        Query query = new Query();
        query.setId(queryDTO.getId());
        query.setKeyword(queryDTO.getKeyword());
        User user = new User();
        query.setUser(user);
        return query;
    }

    static public QueryDTO map(Query query) {
        if (query == null)
            return null;
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setId(query.getId());
        queryDTO.setKeyword(query.getKeyword());
        queryDTO.setAddingDate(query.getAddedDate());
        queryDTO.setHowManyResults(query.getResult().size());

        return queryDTO;
    }

    static public QueryResponseBody mapPaginatedQueryToResponseBody(List<Query> queries){
        QueryResponseBody response = new QueryResponseBody();
        List<QueryDTO> queryDtoList = new ArrayList<>();
        for(Query query: queries){
            queryDtoList.add(map(query));
        }
        response.setSearches(queryDtoList);
        return response;
    }

}
