package pwr.po.webcrawler.service.Query;

import org.springframework.data.domain.Page;
import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.model.user.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Rafał Niedźwiecki on 17.04.2016.
 */
public interface QueryService {

    Set<Query> getAllQueryToUser(User user);

    Page<Query> getAllPageableQueryToUser(User user, int pageNumber, int pageSize);

    void save(Query query);

    Query getQueryById(long id);

    Query getQueryByKeyword(String keyword);

    void deleteById(long id);

    void deleteByKeyword(String keyword);
}
