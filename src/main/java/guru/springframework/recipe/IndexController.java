package guru.springframework.recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final RecipeService recipeServiceImpl;

    @RequestMapping({"", "/", "/index"})
    public String index(Model model) {
        log.debug("showing recipeList in index");
        model.addAttribute("recipeList", recipeServiceImpl.getRecipes());
        return "index";
    }
}
