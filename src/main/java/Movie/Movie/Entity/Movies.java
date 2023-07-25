package Movie.Movie.Entity;

import Movie.Movie.Entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movies extends AbsEntity {
    private String name;
    private String description;
    private String country; //qaysi davlat kinosi
    private Integer year;//qaysi yilda chiqgan
    private String language;//film qaysi tilda
    private String duration;//film necha soat
    private UUID photoId;
    private UUID movieId;
//    @ManyToOne(optional = false)
//    private Company company;
    @ManyToOne(optional = false)
    private Category category;
    private boolean like;
}