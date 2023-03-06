package recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.Recipe;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipe/")
@Validated
public class RecipeRestController {

    private final RecipeService recipeService;

    @GetMapping("{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }
    @PostMapping("new")
    public ResponseEntity<?> addRecipe(
            @Valid
            @RequestBody
            Recipe recipeRequest
    ) {
        return ResponseEntity.ok(recipeService.addRecipe(recipeRequest));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeRecipe(@PathVariable Long id) {
        recipeService.removeRecipe(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable Long id, @Valid @RequestBody Recipe recipeRequest) {recipeService.updateRecipe(id, recipeRequest);}

    @GetMapping("search")
    public ResponseEntity<List<Recipe>> searchRecipeBy(@RequestParam(required = false) String category, @RequestParam(required = false) String name) {
        if (category == null && name == null || category != null && name != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (category != null) return  ResponseEntity.ok(recipeService.searchByCategory(category.toLowerCase()));
        return ResponseEntity.ok(recipeService.searchByName(name.toLowerCase()));
    }
}
