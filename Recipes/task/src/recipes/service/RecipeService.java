package recipes.service;

import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.Direction;
import recipes.entity.Ingredient;
import recipes.entity.Recipe;
import recipes.entity.RecipeRequest;
import recipes.repository.DirectionRepository;
import recipes.repository.IngredientRepository;
import recipes.repository.RecipeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final DirectionRepository directionRepository;
    private final IngredientRepository ingredientRepository;

    public Map<String, ?> addRecipe(RecipeRequest request) {
        Recipe recipe = Recipe.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .lastModification(LocalDateTime.now())
                .build();
        recipeRepository.save(recipe);

        createDirectionsAndSetToRecipe(recipe, request.getDirections());
        createIngredientsAndSetToRecipe(recipe, request.getIngredients());
        return Map.of("id", recipe.getId());
    }

    public Map<String, ?> getRecipe(Long id) {
        try {
            Recipe recipe = recipeRepository.findById(id).orElseThrow();
           return formatRecipeForOutput(recipe);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void removeRecipe(Long id) {
        try {
            Recipe recipe = recipeRepository.findById(id).orElseThrow();
            recipeRepository.delete(recipe);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void updateRecipe(Long id, RecipeRequest recipeRequest) {
        try {
            Recipe recipe = recipeRepository.findById(id).orElseThrow();
            recipe.setName(recipeRequest.getName());
            recipe.setCategory(recipeRequest.getCategory());
            recipe.setLastModification(LocalDateTime.now());
            recipe.setDescription(recipeRequest.getDescription());
            createDirectionsAndSetToRecipe(recipe, recipeRequest.getDirections());
            createIngredientsAndSetToRecipe(recipe, recipeRequest.getIngredients());
            recipeRepository.save(recipe);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void createDirectionsAndSetToRecipe(Recipe recipe, List<String> itemsToFill) {
        List<Direction> directions = new ArrayList<>();
        itemsToFill.forEach(item -> {
            Direction direction = new Direction(item, recipe);
            directionRepository.save(direction);
            directions.add(direction);
        });
        recipe.setDirections(directions);
    }

    public void createIngredientsAndSetToRecipe(Recipe recipe, List<String> itemsToFill) {
        List<Ingredient> ingredients = new ArrayList<>();
        itemsToFill.forEach(item -> {
            Ingredient ingredient = new Ingredient(item, recipe);
            ingredientRepository.save(ingredient);
            ingredients.add(ingredient);
        });
        recipe.setIngredients(ingredients);
    }


    public List<Map<String, ?>> searchByCategory(String category) {
        List<Recipe> recipesByCategory = recipeRepository.findAllByCategory(category);
        return formatListOfRecipeForOutput(recipesByCategory);
    }

    public List<Map<String, ?>> searchByName(String name) {
        List<Recipe> recipesByName = recipeRepository.findAllByName(name);
        return formatListOfRecipeForOutput(recipesByName);
    }

    public List<Map<String, ?>> formatListOfRecipeForOutput(List<Recipe> recipes) {
        return recipes.stream()
                .map(this::formatRecipeForOutput)
                .collect(Collectors.toList());
    }

    public Map<String, ?> formatRecipeForOutput(Recipe recipe) {
        return Map.of(
                "name", recipe.getName(),
                "description", recipe.getDescription(),
                "ingredients", recipe.getIngredients()
                        .stream()
                        .map(Ingredient::getName)
                        .collect(Collectors.toList()),
                "directions", recipe.getDirections()
                        .stream()
                        .map(Direction::getDescription)
                        .collect(Collectors.toList())
        );
    }
}
