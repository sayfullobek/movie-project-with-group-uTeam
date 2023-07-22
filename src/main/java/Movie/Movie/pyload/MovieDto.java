package Movie.Movie.pyload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private String name;
    private String description;
    private String country;
    private Integer year;
    private String language;
    private String duration;
    private UUID movieId;
    private UUID photoId;
    private UUID companyId;
    private Integer categoryId;
    private boolean like;
}
