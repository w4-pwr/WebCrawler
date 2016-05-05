package pwr.po.webcrawler.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.service.Query.QueryService;
import pwr.po.webcrawler.web.controller.QueryController;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.service.user.UserService;
import pwr.po.webcrawler.web.dto.QueryDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Created by Rafał Niedźwiecki on 18.04.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class QueryControllerTest {

    @InjectMocks
    private QueryController queryControllerMock = new QueryController();

    @Mock
    QueryService queryService;

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    @Autowired
    ApplicationContext context;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(queryControllerMock).build();
    }

    @Test
    public void AddQuery_Success() throws Exception
    {
        User user = new User();
        user.setId(10);
        QueryDTO query = new QueryDTO();
        query.setKeyword("PiesoKot");
        query.setUserId(10);
        when(userService.getUser(10)).thenReturn(user);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(query);
        mockMvc.perform(put("/query")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

    }

    @Test
    public void AddQuery_BodyNull() throws Exception
    {
        mockMvc.perform(put("/query"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void AddQuery_UserIdLessThanZero() throws Exception
    {
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setUserId(-20);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(queryDTO);
        when(userService.getUser(-20)).thenReturn(null);

        mockMvc.perform(put("/query")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void GetAllQueryToUser_UserExist() throws Exception
    {
        User user = new User();
        user.setId(1);

        Query testQuery = new Query();
        testQuery.setKeyword("Cat");
        testQuery.setId(12l);
        testQuery.setUser(user);
        Set<Query> list = new HashSet<>();
        list.add(testQuery);
        user.setQuery(list);


        when(userService.getUser(1)).thenReturn((user));
        when(queryService.getAllQueryToUser(user)).thenReturn(list);


        mockMvc.perform(get("/query").param("user_id","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(12)))
                .andExpect(jsonPath("$[0].userId",is(1)))
                .andExpect(jsonPath("$[0].keyword",is("Cat")))
                .andExpect(jsonPath("$[0].result",isEmptyOrNullString()));
    }

    @Test
    public void GetAllQueryToUser_UserNotExist() throws Exception
    {
        when(userService.getUser(1)).thenReturn(null);
        mockMvc.perform(get("/query")).andExpect(status().isBadRequest());
    }

    @Test
    public void GetAllQueryToUser_NoQueries() throws Exception
    {
        User user = new User();
        user.setId(1);
        Set<Query> list = new HashSet<>();
        user.setQuery(list);

        when(userService.getUser(1)).thenReturn((user));
        when(queryService.getAllQueryToUser(user)).thenReturn(list);

        mockMvc.perform(get("/query").param("user_id","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }



}


