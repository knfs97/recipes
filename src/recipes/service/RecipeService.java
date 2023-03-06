package recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import recipes.entity.Recipe;

import recipes.entity.User;
import recipes.repository.RecipeRepository;
import recipes.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public Map<String, ?> addRecipe(Recipe recipe) {
        User user = getAuthenticatedUser();
        recipe.setDate(LocalDateTime.now());
        recipe.setUser(user);
        recipeRepository.save(recipe);
        return Map.of("id", recipe.getId());
    }

    public Recipe getRecipe(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void removeRecipe(Long id) {
        User user = getAuthenticatedUser();
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!recipe.getUser().equals(user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        recipeRepository.delete(recipe);
    }

    public void updateRecipe(Long id, Recipe recipeRequest) {
        User user = getAuthenticatedUser();
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!recipe.getUser().equals(user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        recipe.setName(recipeRequest.getName());
        recipe.setCategory(recipeRequest.getCategory());
        recipe.setDate(LocalDateTime.now());
        recipe.setDescription(recipeRequest.getDescription());
        recipe.setDirections(recipeRequest.getDirections());
        recipe.setIngredients(recipeRequest.getIngredients());
        recipeRepository.save(recipe);
    }

    public List<Recipe> searchByCategory(String category) {
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> searchByName(String name) {
        return recipeRepository.findAllByNameIgnoreCaseContainsOrderByDateDesc(name);
    }

    public User getAuthenticatedUser() {
        try {
            String authUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(authUserEmail).orElseThrow();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
