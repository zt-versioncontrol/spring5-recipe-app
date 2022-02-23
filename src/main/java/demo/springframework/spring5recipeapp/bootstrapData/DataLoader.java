package demo.springframework.spring5recipeapp.bootstrapData;

import demo.springframework.spring5recipeapp.domain.*;
import demo.springframework.spring5recipeapp.services.CategoryService;
import demo.springframework.spring5recipeapp.services.RecipeService;
import demo.springframework.spring5recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {
    private final RecipeService recipeService;
    private final CategoryService categoryService;
    private final UnitOfMeasureService unitOfMeasureService;

    private Category mexicanCategory;
    private UnitOfMeasure ripe;
    private UnitOfMeasure teaspoon;
    private UnitOfMeasure tablespoon;
    private UnitOfMeasure pinch;
    private UnitOfMeasure slice;
    private UnitOfMeasure chip;

    public DataLoader(RecipeService recipeService, CategoryService categoryService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.debug("Loading Categories from Database...");
        initializeCategories();
        log.debug("Loading Unites of Measure from Database...");
        initializeUnitsOfMeasure();
        log.debug("Bootstrapping Database with Guacamole Recipe...");
        saveGuacamole();
    }

    private void initializeCategories(){
        mexicanCategory = categoryService.findByDescription("Mexican");
    }

    private void initializeUnitsOfMeasure(){
        ripe = unitOfMeasureService.findByDescription("Ripe");
        teaspoon = unitOfMeasureService.findByDescription("Teaspoon");
        tablespoon = unitOfMeasureService.findByDescription("Tablespoon");
        pinch = unitOfMeasureService.findByDescription("Pinch");
        slice = unitOfMeasureService.findByDescription("Slice");
        chip = unitOfMeasureService.findByDescription("Chip");
    }

    private void saveGuacamole() throws IOException{
        Recipe guacamole = new Recipe();
        guacamole.setName("Guacamole");
        guacamole.getCategories().add(mexicanCategory);
        guacamole.setDescription(BootstrapDataStrings.GUACAMOLE_DESCRIPTION);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setPreparationTime(10);
        guacamole.setCookTime(10);
        guacamole.setServings(3);
        guacamole.setSource(BootstrapDataStrings.GUACAMOLE_SOURCE);
        guacamole.setUrl(BootstrapDataStrings.GUACAMOLE_URL);
        guacamole.setDirections(BootstrapDataStrings.GUACAMOLE_DIRECTIONS);
        guacamole.getIngredients().addAll(createGuacamoleIngredients(guacamole));
        guacamole.setImage(loadImageByteArray(BootstrapDataStrings.GUACAMOLE_IMAGE_PATH, BootstrapDataStrings.GUACAMOLE_IMAGE_FORMAT));
        guacamole.setNotes(new Notes(guacamole, BootstrapDataStrings.GUACAMOLE_NOTES));

        recipeService.save(guacamole);
    }

    private List<Ingredient> createGuacamoleIngredients(Recipe guacamoleRecipe){
        Ingredient avocado = new Ingredient("Avocado", new BigDecimal(2), guacamoleRecipe, ripe);
        Ingredient salt = new Ingredient("Salt", new BigDecimal(0.25), guacamoleRecipe, teaspoon);
        Ingredient lime = new Ingredient("Fresh Lime or Lemon Juice", new BigDecimal(1), guacamoleRecipe, tablespoon);
        Ingredient onion = new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(3), guacamoleRecipe, tablespoon);
        Ingredient serrano = new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", new BigDecimal(2), guacamoleRecipe, ripe);
        Ingredient cilantro = new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), guacamoleRecipe, tablespoon);
        Ingredient pepper = new Ingredient("freshly ground black pepper", new BigDecimal(1), guacamoleRecipe, pinch);
        Ingredient tomato = new Ingredient("tomato, chopped", new BigDecimal(0.5), guacamoleRecipe, ripe);
        Ingredient radish = new Ingredient("Red radish", new BigDecimal(5), guacamoleRecipe, slice);
        Ingredient tortilla = new Ingredient("Tortilla", new BigDecimal(10), guacamoleRecipe, chip);

        return Arrays.asList(avocado, salt, lime, onion, serrano, cilantro, pepper, tomato, radish, tortilla);
    }

    private byte[] loadImageByteArray(String imageFilePath, String imageFormat) throws IOException {
        File imageFile = new File(imageFilePath);
        BufferedImage image = ImageIO.read(imageFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, imageFormat, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
}
