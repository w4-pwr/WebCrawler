package pwr.po.webcrawler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rafał Niedźwiecki on 17.04.2016.
 */

@RestController
@RequestMapping("query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @Autowired
    private UserService userService;

    public static int MAX_PAGE_SIZE = 100;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<QueryDTO>> get(@RequestParam Long user_id, @RequestParam int page,@RequestParam int size) {
        User user = userService.getUser(user_id);
        if(user==null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PagedListHolder<Query> keywordList = new PagedListHolder<>(new ArrayList<>(user.getQuery()));

        if(size>MAX_PAGE_SIZE)
            size=MAX_PAGE_SIZE;

        keywordList.setPageSize(size);
        keywordList.setPage(page);

        List<Query> pageListItems=keywordList.getPageList();
        List<QueryDTO> result = new ArrayList<>();

        for(Query query : pageListItems)
        {
            QueryDTO dto = QueryMapper.map(query);
            result.add(dto);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
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
