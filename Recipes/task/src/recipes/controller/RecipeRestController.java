package recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.entity.Recipe;
import recipes.service.RecipeService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipe/")
@Validated
public class RecipeRestController {

    private final RecipeService recipeService;

    @GetMapping("{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @PostMapping("new")
    public ResponseEntity<?> addRecipe(
            @Valid
            @RequestBody
            Recipe recipe
    ) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeRecipe(@PathVariable Long id) {
        recipeService.removeRecipe(id);
    }
}
