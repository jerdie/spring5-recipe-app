package guru.springframework.bootstrap;

import guru.springframework.category.Category;
import guru.springframework.category.CategoryRepository;
import guru.springframework.others.Difficulty;
import guru.springframework.others.Ingredient;
import guru.springframework.recipe.Recipe;
import guru.springframework.recipe.RecipeRepository;
import guru.springframework.uom.UnitOfMeasure;
import guru.springframework.uom.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    public List<Recipe> getRecipes() {
        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaspoonOptional.isPresent()) {
            throw new RuntimeException("Teaspon does not exist!");
        }

        UnitOfMeasure teaspoon = teaspoonOptional.get();

        Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanOptional.isPresent()) {
            throw new RuntimeException("Mexican category does not exist!");
        }

        Category mexican = mexicanOptional.get();

        Recipe guacamole = new Recipe();
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.getCategories().add(mexican);
        guacamole.setDirections("Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "How to make guacamole - scoring avocado\n" +
                "Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "How to make guacamole - smashing avocado with fork\n" +
                "Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "\n" +
                "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");
        guacamole.setDescription("Guacamole");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        Ingredient avocados = new Ingredient();
        avocados.setDescription("Avocados");
        avocados.setAmount(new BigDecimal(2));
        Ingredient salt = new Ingredient();
        salt.setDescription("Salt");

        salt.setUnitOfMeasure(teaspoon);
        salt.setAmount(new BigDecimal(".25"));

        guacamole.addIngredient(avocados);
        guacamole.addIngredient(salt);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(guacamole);
        return recipes;
    }

}
