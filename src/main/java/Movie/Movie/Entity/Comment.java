package Movie.Movie.Entity;

import Movie.Movie.Entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment extends AbsEntity {
    @Column(nullable = false)
    private String comment;
    @ManyToOne
    private Movies movies;
}
