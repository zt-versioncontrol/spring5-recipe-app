package demo.springframework.spring5recipeapp.controllers;


import demo.springframework.spring5recipeapp.services.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipeList", recipeService.findAll());

        return "index";
    }

    @RequestMapping(value = "/recipeImg/{recipeId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getRecipeImg(@PathVariable Long recipeId){
        return recipeService.findById(recipeId).getImage();
    }
}
