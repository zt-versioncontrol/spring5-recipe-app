package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.domain.Ingredient;
import demo.springframework.spring5recipeapp.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient add(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }
}
