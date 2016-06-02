package pwr.po.webcrawler.service.Query;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
=======
>>>>>>> Query
import org.springframework.stereotype.Service;
import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.repository.QueryRepository;

import java.util.List;
<<<<<<< HEAD
import java.util.Set;
=======
>>>>>>> Query

/**
 * Created by Rafał Niedźwiecki on 17.04.2016.
 */
@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    QueryRepository queryRepository;

    @Override
<<<<<<< HEAD
    public Set<Query> getAllQueryToUser(User user) {
=======
    public List<Query> getAllQueryToUser(User user) {
>>>>>>> Query
        return queryRepository.findByUser(user);
    }

    @Override
<<<<<<< HEAD
    public Page<Query> getAllPageableQueryToUser(User user, int pageNumber, int pageSize) {
        return queryRepository.findAllByUser(user, new PageRequest(pageNumber,pageSize));
    }

    @Override
=======
>>>>>>> Query
    public void save(Query query) {
        queryRepository.save(query);
    }

    @Override
    public Query getQueryById(long id) {
        return queryRepository.findById(id);
    }

    @Override
    public Query getQueryByKeyword(String keyword) {
        return queryRepository.findByKeyword(keyword);
    }

    @Override
    public void deleteById(long id) {
        queryRepository.deleteById(id);
    }

    @Override
    public void deleteByKeyword(String keyword) {
        queryRepository.deleteByKeyword(keyword);
    }

}
