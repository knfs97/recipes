package recipes.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Directions {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
