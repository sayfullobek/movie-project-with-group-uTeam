package Movie.Movie.Repository;

import Movie.Movie.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin
public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
