package recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.RecipeRequest;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
            RecipeRequest recipeRequest
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
    public void updateRecipe(@PathVariable Long id, @Valid @RequestBody RecipeRequest recipeRequest) {recipeService.updateRecipe(id, recipeRequest);}

    @GetMapping("search")
    public ResponseEntity<List<Map<String, ?>>> searchRecipeBy(@RequestParam(required = false) String category, @RequestParam(required = false) String name) {
        if (category == null && name == null || category != null && name != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (category != null) return ResponseEntity.of(recipeService.searchByCategory(category.toLowerCase()));
        if (name != null) return ResponseEntity.of( recipeService.searchByName(name.toLowerCase()));
        return null;
    }
}
