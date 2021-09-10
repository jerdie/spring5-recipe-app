package guru.springframework.recipe.ingredient;

import guru.springframework.recipe.RecipeCommand;
import guru.springframework.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String ingredients(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        model.addAttribute("recipe", recipeCommand);
        return "/recipe/ingredient/list";
    }
}
