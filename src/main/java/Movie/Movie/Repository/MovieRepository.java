package Movie.Movie.Repository;

import Movie.Movie.Entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;

@CrossOrigin
public interface MovieRepository extends JpaRepository<Movies, UUID> {
    List<Movies> findByCategoryName(String category_name);
//    @Query("select * from Movies where name =?%1%")
//    List<Movies> findByName(String name);
}
