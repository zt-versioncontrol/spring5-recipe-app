package demo.springframework.spring5recipeapp.controllers;

import demo.springframework.spring5recipeapp.services.IngredientService;
import demo.springframework.spring5recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IngredientController {
    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listRecipeIngredients(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "ingredient/recipe_ingredients";
    }

    @GetMapping("ingredient/show/{ingredientId}")
    public String show(@PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findCommandById(Long.valueOf(ingredientId)));

        return "ingredient/show";
    }
}
