package pwr.po.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.model.user.User;

import java.util.List;

/**
 * Created by Rafał Niedźwiecki on 17.04.2016.
 */
@Repository
public interface QueryRepository extends JpaRepository<Query,Long> {

    Query findById(long id);

    List<Query> findByUser(User user);

    Query findByKeyword(String keyword);

    void deleteByKeyword(String keyword);

    void deleteById(long id);
}
