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

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> saveQuery(@RequestBody QueryDTO queryDTO)
    {
        if(queryDTO==null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(queryDTO.getUserId()<0)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        queryDTO.setAddedDate(new Date());
        queryService.save(QueryMapper.map(queryDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
