package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
