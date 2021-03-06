package pwr.po.webcrawler.service.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.model.user.User;
import pwr.po.webcrawler.repository.QueryRepository;

import java.util.List;
import java.util.Set;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    QueryRepository queryRepository;

    @Override
    public Page<Query> getAllPageableQueryToUser(User user, int pageNumber, int pageSize) {
        return queryRepository.findAllByUser(user, new PageRequest(pageNumber,pageSize));
    }

    @Override
    public void save(Query query) {
        queryRepository.save(query);
    }

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
