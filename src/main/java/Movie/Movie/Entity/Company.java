package Movie.Movie.Entity;

import Movie.Movie.Entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Company extends AbsEntity {
    @Column(nullable = false)
    private String name;
}