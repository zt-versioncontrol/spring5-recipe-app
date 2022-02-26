package demo.springframework.spring5recipeapp.services;

import demo.springframework.spring5recipeapp.commands.RecipeCommand;
import demo.springframework.spring5recipeapp.converters.*;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        Recipe recipe = new Recipe();
        List<Recipe> recipesData = new ArrayList<>();
        recipesData.add(recipe);

        Mockito.when(recipeService.findAll()).thenReturn(recipesData);

        List<Recipe> recipes = recipeService.findAll();
        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void findById(){
        Recipe newRecipe = new Recipe();
        newRecipe.setId(5L);
        Optional<Recipe> newRecipeOptional = Optional.of(newRecipe);

        when(recipeRepository.findById(anyLong())).thenReturn(newRecipeOptional);

        Recipe persistedRecipe = recipeService.findById(5L);

        assertNotNull(persistedRecipe);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipeCommandByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        Mockito.when(recipeToRecipeCommand.convert(Mockito.any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById(1L);

        assertNotNull(commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipesTest() throws Exception {

        Recipe recipe = new Recipe();
        List<Recipe> receipes = new ArrayList<>();
        receipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn(receipes);

        List<Recipe> recipes = recipeService.findAll();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void testDeleteById() throws Exception {

        Long idToDelete = Long.valueOf(2L);

        recipeService.deleteById(idToDelete);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}