package Movie.Movie.Entity;

import Movie.Movie.Entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movie extends AbsEntity {
    @Column(nullable = false, precision = 10000)
    private String description;
    private
    @Column(nullable = false)
    private UUID movieId;
}


