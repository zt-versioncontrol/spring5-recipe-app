package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.commands.IngredientCommand;
import demo.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import demo.springframework.spring5recipeapp.domain.Ingredient;
import demo.springframework.spring5recipeapp.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public Ingredient add(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    @Override
    public IngredientCommand findCommandById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);

        return ingredientToIngredientCommand.convert(ingredient);
    }
}
