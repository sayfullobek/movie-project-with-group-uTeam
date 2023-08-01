package Movie.Movie.service;

import Movie.Movie.Entity.Comment;
import Movie.Movie.Entity.Movies;
import Movie.Movie.Repository.CommentRepository;
import Movie.Movie.Repository.MovieRepository;
import Movie.Movie.pyload.ApiResponse;
import Movie.Movie.pyload.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MovieRepository movieRepository;

    public ApiResponse sendComment(CommentDto commentDto) {
        try {
            Movies getMovie = movieRepository.findById(commentDto.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("getMovie"));
            Comment build = Comment.builder()
                    .comment(commentDto.getComment())
                    .movies(getMovie)
                    .build();
            commentRepository.save(build);
            return new ApiResponse("Comment yuborildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xato", true);
        }
    }
}
