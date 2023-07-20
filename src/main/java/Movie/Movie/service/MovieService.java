package Movie.Movie.service;

import Movie.Movie.Entity.Category;
import Movie.Movie.Entity.Company;
import Movie.Movie.Entity.Movie;
import Movie.Movie.Repository.CategoryRepository;
import Movie.Movie.Repository.CompanyRepository;
import Movie.Movie.Repository.MovieRepository;
import Movie.Movie.pyload.ApiResponse;
import Movie.Movie.pyload.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;

    public ApiResponse addMovie(MovieDto dto) {
        try {
            Company getCompany = companyRepository.findById(dto.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("getCompany"));
            Category getCategory = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("getCategory"));
            Movie build = Movie.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .country(dto.getCountry())
                    .year(dto.getYear())
                    .language(dto.getLanguage())
                    .movieId(dto.getMovieId())
                    .company(getCompany)
                    .category(getCategory)
                    .like(false)
                    .build();
            movieRepository.save(build);
            return new ApiResponse("Film Saqlandi", true);
        } catch (Exception e) {
            return new ApiResponse("Film saqlashda hatolik", false);
        }
    }

    public ApiResponse editMovie(UUID movieId, MovieDto dto) {
        try {
            Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("getMovie"));
            Category getCategory = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("getCategory"));
            movie.setCategory(getCategory);
            movie.setCountry(dto.getCountry());
            movie.setLanguage(dto.getLanguage());
            movie.setDuration(dto.getDuration());
            movie.setName(dto.getName());
            movie.setYear(dto.getYear());
            movie.setDescription(dto.getDescription());
            movieRepository.save(movie);
            return new ApiResponse("Film tahrirlandi", true);
        } catch (Exception e) {
            return new ApiResponse("Film tahrirlashda hatolik", false);
        }
    }

    public ApiResponse deleteMovie(UUID movieId) {
        try {
            Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("getMovie"));
            movieRepository.delete(movie);
            return new ApiResponse("Film O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Film O'chirilmadi", false);
        }
    }

    public ApiResponse like(UUID movieId) {
        try {
            Movie movie = movieRepository.findById(movieId).orElseThrow(ResourceNotFoundException::new);
            movie.setLike(true);
            return new ApiResponse("Filmga like bosdingiz", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik", false);
        }
    }

}
