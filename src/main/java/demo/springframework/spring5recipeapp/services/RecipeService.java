package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.commands.RecipeCommand;
import demo.springframework.spring5recipeapp.domain.Recipe;

import java.util.List;

public interface RecipeService {


    Recipe save(Recipe recipe);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    List<Recipe> findAll();
    Recipe findById(Long id);
}
