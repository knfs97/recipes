package recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.Recipe;
import recipes.repository.RecipeRepository;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    public Map<String, ?> addRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return Map.of("id", recipe.getId());
    }
    public Recipe getRecipe(Long id) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(id);
        if (recipeOpt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return recipeOpt.get();
    }

    public void removeRecipe(Long id) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(id);
        if (recipeOpt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        recipeRepository.delete(recipeOpt.get());
    }


}
