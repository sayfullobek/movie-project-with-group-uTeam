package Movie.Movie.pyload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private boolean success;

}
