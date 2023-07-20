package Movie.Movie.Entity;

import Movie.Movie.Entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor

@Builder
@Entity
public class Category extends AbsNameEntity {

}
