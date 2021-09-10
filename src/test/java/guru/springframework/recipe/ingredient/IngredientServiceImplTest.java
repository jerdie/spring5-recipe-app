package guru.springframework.recipe.ingredient;

import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipe.Recipe;
import guru.springframework.recipe.RecipeCommand;
import guru.springframework.recipe.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeService recipeService;

    IngredientServiceImpl ingredientService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeService, ingredientToIngredientCommand);
    }

    @Test
    public void testFindByRecipeIdAndId() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeService, times(1)).findById(anyLong());
    }
}
