package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.domain.Category;
import demo.springframework.spring5recipeapp.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements  CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByDescription(String description){
        return categoryRepository.findByDescription(description).get();
    }
}
