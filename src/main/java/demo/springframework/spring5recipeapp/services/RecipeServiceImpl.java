package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.commands.RecipeCommand;
import demo.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import demo.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import demo.springframework.spring5recipeapp.domain.Recipe;
import demo.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detatchedRecipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(detatchedRecipe);

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public List<Recipe> findAll(){
        log.debug("Recipe service: findAll");
        var recipies = recipeRepository.findAll();
        List<Recipe> recipeList = new ArrayList<>();
        for(Recipe recipe : recipies){
            recipeList.add(recipe);
        }

        return recipeList;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }
}
