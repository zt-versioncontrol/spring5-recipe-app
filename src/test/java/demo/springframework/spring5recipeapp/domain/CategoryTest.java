package demo.springframework.spring5recipeapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        category.setId(5L);
        assertEquals(category.getId(), 5L);
    }

    @Test
    void getDescription() {
        category.setDescription("abc");
        assertEquals(category.getDescription(), "abc");
    }

    @Test
    void setRecipes() {
        assertEquals(category.getRecipes(), null);
    }
}