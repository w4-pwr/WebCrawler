package pwr.po.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.po.webcrawler.model.User;

/**
 * Created by pawel on 07.12.15.
 */

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
