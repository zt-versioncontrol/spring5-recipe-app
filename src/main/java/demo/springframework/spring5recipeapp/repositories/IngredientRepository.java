package demo.springframework.spring5recipeapp.repositories;

import demo.springframework.spring5recipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
