package pwr.po.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.po.webcrawler.model.User;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

     User findByUsername(String username);

     List<User> findByFirstNameAndLastName(String firstName, String lastName);

     User findByEmail(String email);

     void deleteById(long id);

}
