package recipes.entity;

import jdk.jfr.Category;
import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotEmpty
    private List<String> ingredients;
    @NotEmpty
    private List<String> directions;

    @NotBlank
    private String category;
}
