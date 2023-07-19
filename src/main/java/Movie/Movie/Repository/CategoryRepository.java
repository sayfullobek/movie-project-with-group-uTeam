package Movie.Movie.Repository;

import Movie.Movie.Entity.Category;
import Movie.Movie.projection.CustomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "list", path = "category", excerptProjection = CustomCategory.class)

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}