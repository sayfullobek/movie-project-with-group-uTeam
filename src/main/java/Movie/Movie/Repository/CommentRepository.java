package Movie.Movie.Repository;

import Movie.Movie.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;
@CrossOrigin
public interface  CommentRepository extends JpaRepository<Comment, UUID> {
    @Query("select comment from Comment comment where comment.movies.id=?1")
    List<Comment> findCommentsByMoviesId(UUID movies_id);
}
