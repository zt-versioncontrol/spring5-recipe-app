package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.domain.Recipe;
import demo.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        Recipe recipe = new Recipe();
        List<Recipe> recipesData = new ArrayList<>();
        recipesData.add(recipe);

        Mockito.<List<Recipe>>when(recipeService.findAll()).thenReturn(recipesData);

        List<Recipe> recipes = recipeService.findAll();
        assertEquals(1, recipes.size());
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findById(){
        Recipe newRecipe = new Recipe();
        newRecipe.setId(5L);
        Optional<Recipe> newRecipeOptional = Optional.of(newRecipe);

        Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(newRecipeOptional);

        Recipe persistedRecipe = recipeService.findById(5L);

        assertNotNull(persistedRecipe);
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(recipeRepository, Mockito.never()).findAll();
    }
}