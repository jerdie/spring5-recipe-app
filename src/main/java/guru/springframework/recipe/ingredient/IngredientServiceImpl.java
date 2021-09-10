package guru.springframework.recipe.ingredient;

import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.recipe.Recipe;
import guru.springframework.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {
    public final RecipeService recipeService;

    public final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Override
    public IngredientCommand findByRecipeIdAndId(Long recipeId, Long id) {
        Recipe recipe = recipeService.findById(recipeId);
        Ingredient ingredient = recipe.getIngredients().stream()
                .filter(i-> i.id.equals(id))
                .findFirst()
                .orElse(null);
        return ingredientToIngredientCommand.convert(ingredient);
    }
}
