package Movie.Movie.controller;

import Movie.Movie.Entity.Movies;
import Movie.Movie.Repository.MovieRepository;
import Movie.Movie.pyload.ApiResponse;
import Movie.Movie.pyload.MovieDto;
import Movie.Movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {
    private final MovieService movieService;
    private final MovieRepository movieRepository;

    @GetMapping
    public HttpEntity<?> getMovie() {
        List<Movies> all = movieRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{movieId}")
    public HttpEntity<?> getOneMovie(@PathVariable UUID movieId) {
        Movies movie = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("getMovie"));
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public HttpEntity<?> addMovie(@RequestBody MovieDto movieDto) {
        Movies movies = movieService.addMovie(movieDto);
        return ResponseEntity.status(movies != null ? 200 : 409).body(movies);
    }
    @PutMapping("/upload-vd/{id}")
    public HttpEntity<?> uploadVd(@PathVariable UUID id, @RequestParam(name = "vidId") UUID movieId){
        Movies movies = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("movie topilmadi"));
        movies.setMovieId(movieId);
        movieRepository.save(movies);
        return ResponseEntity.ok(new ApiResponse("video saqlandi", true));
    }

    @PutMapping("/{movieId}")
    public HttpEntity<?> editMovie(@PathVariable UUID movieId, @RequestBody MovieDto dto) {
        ApiResponse apiResponse = movieService.editMovie(movieId, dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{movieId}")
    public HttpEntity<?> deleteMovie(@PathVariable UUID movieId) {
        ApiResponse apiResponse = movieService.deleteMovie(movieId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{name}")
    public HttpEntity<?> getMovieByCategory(@PathVariable String name) {
        List<Movies> byCategoryName = movieRepository.findByCategoryName(name);
        return ResponseEntity.ok(byCategoryName);
    }
}
