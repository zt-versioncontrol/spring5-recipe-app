package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureService {
    UnitOfMeasure findByDescription(String description);
}
