package recipes.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotEmpty
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    private List<Ingredient> ingredients;

    @NotEmpty
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    private List<Directions> directions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Recipe recipe = (Recipe) o;
        return getId() != null && Objects.equals(getId(), recipe.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
