package Movie.Movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieApplication {
    public static void main(String[] args) {
        System.err.println("Hello Tashkent");
        SpringApplication.run(MovieApplication.class, args);
    }

}
