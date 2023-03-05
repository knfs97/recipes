package recipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direction {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @NotBlank
    private String description;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Direction(String description, Recipe recipe) {
        this.description = description;
        this.recipe = recipe;
    }
}
