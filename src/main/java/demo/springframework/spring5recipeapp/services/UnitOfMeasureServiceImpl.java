package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.domain.UnitOfMeasure;
import demo.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public UnitOfMeasure findByDescription(String description){
        return unitOfMeasureRepository.findByDescription(description).get();
    }

}
