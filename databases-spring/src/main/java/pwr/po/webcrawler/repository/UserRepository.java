package pwr.po.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.po.webcrawler.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
