package pwr.po.webcrawler.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.po.webcrawler.model.Query;
import pwr.po.webcrawler.model.user.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Rafał Niedźwiecki on 17.04.2016.
 */
@Repository
public interface QueryRepository extends JpaRepository<Query,Long> {

    Query findById(long id);

    Set<Query> findByUser(User user);

    Page<Query> findAllByUser(User user, Pageable pageable);

    Query findByKeyword(String keyword);

    void deleteByKeyword(String keyword);

    void deleteById(long id);
}
