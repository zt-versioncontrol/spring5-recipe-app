package demo.springframework.spring5recipeapp.repositories;

import demo.springframework.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategorRepository extends CrudRepository<Category, Long> {
}
