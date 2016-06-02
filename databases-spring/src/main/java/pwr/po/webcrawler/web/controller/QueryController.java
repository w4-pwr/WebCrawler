package pwr.po.webcrawler.web.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.Query.QueryService;
import pwr.po.webcrawler.service.user.UserService;
import pwr.po.webcrawler.web.dto.QueryDTO;
import pwr.po.webcrawler.web.mapper.QueryMapper;
import pwr.po.webcrawler.web.request.QueryRequestBody;
import pwr.po.webcrawler.web.response.QueryResponseBody;
import pwr.po.webcrawler.web.response.ResultResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rafał Niedźwiecki on 17.04.2016.
 */

@RestController
public class QueryController {

    public static final int PAGE_SIZE = 30;

    @Autowired
    private QueryService queryService;

    @Autowired
    private UserService userService;

    public static int MAX_PAGE_SIZE = 100;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<QueryResponseBody> getAllQuery(@RequestParam("token") String token, @RequestParam int page) {
        User user = userService.getUserByToken(token);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Page<Query> allPageableQueryToUser = queryService.getAllPageableQueryToUser(user, page, PAGE_SIZE);

        QueryResponseBody queryResponseBody = QueryMapper.mapPaginatedQueryToResponseBody(allPageableQueryToUser.getContent());

        return new ResponseEntity<>(queryResponseBody,HttpStatus.OK);
    }


    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public ResponseEntity<ResultResponseBody> getQueriesResults(@RequestParam("token") String token, @RequestParam int id,@RequestParam int page) {

        Query queryById = queryService.getQueryById(id);
        ResultResponseBody response = new ResultResponseBody(queryById);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }




    @RequestMapping(value = "/query/", method = RequestMethod.POST)
    public ResponseEntity<String> saveQuery(@RequestParam("token") String token, @RequestBody QueryRequestBody requestBody)
    {
        if(requestBody==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserByToken(token);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Query query = prepareNewQueryForSaving(requestBody, user);
        queryService.save(query);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Query prepareNewQueryForSaving(QueryRequestBody requestBody, User user) {
        Query query = new Query();
        query.setUser(user);
        query.setAddedDate(new Date());
        query.setKeyword(requestBody.getKeyword());
        return query;
    }
}
