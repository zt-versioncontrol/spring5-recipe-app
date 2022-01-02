package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.domain.Category;

public interface CategoryService {


    Category findByDescription(String description);
}
