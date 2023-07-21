package Movie.Movie.Repository;

import Movie.Movie.Entity.Category;
import Movie.Movie.projection.CustomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "list", path = "category", excerptProjection = CustomCategory.class)
@CrossOrigin
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}