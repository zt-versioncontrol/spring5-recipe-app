package demo.springframework.spring5recipeapp.controllers;

import demo.springframework.spring5recipeapp.commands.RecipeCommand;
import demo.springframework.spring5recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String createRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/form";
    }
    @RequestMapping("recipe/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/form";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/show/" + savedCommand.getId();
    }

    @GetMapping("/recipe/delete/{id}")
    public String delete(@PathVariable String id){
        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
