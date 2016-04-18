package pwr.po.webcrawler.service.Query;

import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.model.user.User;

import java.util.List;

/**
 * Created by Rafał Niedźwiecki on 17.04.2016.
 */
public interface QueryService {

    List<Query> getAllQueryToUser(User user);

    void save(Query query);

    Query getQueryById(long id);

    Query getQueryByKeyword(String keyword);

    void deleteById(long id);

    void deleteByKeyword(String keyword);
}
