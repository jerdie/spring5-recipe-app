package guru.springframework.recipe.ingredient;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndId(Long recipeId, Long id);
}
