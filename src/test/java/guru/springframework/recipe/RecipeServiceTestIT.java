package guru.springframework.recipe;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceTestIT {
    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Transactional
    @Test
    public void testSavedOfDescription() {
        // GIVEN
        Set<Recipe> recipes = recipeService.getRecipes();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(testRecipe);
        recipeCommand.setDescription(NEW_DESCRIPTION);

        // WHEN
        RecipeCommand recipeCommandResult = recipeService.saveRecipeCommand(recipeCommand);

        //THEN
        assertEquals(NEW_DESCRIPTION,recipeCommandResult.getDescription());
        assertEquals(testRecipe.getId(), recipeCommandResult.getId());
        assertEquals(testRecipe.getCategories().size(), recipeCommandResult.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), recipeCommandResult.getIngredients().size());
    }
}