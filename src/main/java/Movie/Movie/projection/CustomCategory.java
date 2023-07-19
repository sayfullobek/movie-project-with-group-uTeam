package Movie.Movie.projection;

import Movie.Movie.Entity.Category;
import org.springframework.data.rest.core.config.Projection;


@Projection(name = "customCategory", types = Category.class)

public interface CustomCategory {
    Integer getId();

    String getName();
}
