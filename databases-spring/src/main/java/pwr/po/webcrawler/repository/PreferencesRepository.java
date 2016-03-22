package pwr.po.webcrawler.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.po.webcrawler.model.Preferences;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Long>{
}
