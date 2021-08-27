package guru.springframework.recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RecipeServiceImpl recipeServiceImpl;

    public IndexController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
    }

    @RequestMapping({"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("recipeList", recipeServiceImpl.getRecipes());
        return "index";
    }
}
