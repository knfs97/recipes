package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.entity.Recipe;
import recipes.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<Recipe> findByIdAndUser(Long id, User user);
    List<Recipe> findAllByCategoryIgnoreCaseAndUserOrderByDateDesc(String category, User user);
    List<Recipe> findAllByNameIgnoreCaseContainsAndUserOrderByDateDesc(String name, User user);
}
