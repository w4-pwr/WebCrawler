package pwr.po.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.po.webcrawler.model.CurrentUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CurrentUser,Long> {

    Optional<CurrentUser> findByUsername(String Username);
}
