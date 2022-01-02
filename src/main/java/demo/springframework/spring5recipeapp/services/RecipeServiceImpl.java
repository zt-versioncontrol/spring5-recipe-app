package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.domain.Recipe;
import demo.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> findAll(){
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
}
