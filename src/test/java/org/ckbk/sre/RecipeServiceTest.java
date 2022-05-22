package org.ckbk.sre;

import org.apache.commons.io.FileUtils;
import org.ckbk.sre.exceptions.*;
import org.ckbk.sre.services.FileSystemService;
import org.ckbk.sre.services.RecipeService;
import org.ckbk.sre.services.RecipeService;
import org.ckbk.sre.services.UserService;
import org.dizitart.no2.Nitrite;
import org.junit.jupiter.api.*;
import org.ckbk.sre.model.Recipe;

import java.util.ArrayList;

import static org.ckbk.sre.services.FileSystemService.getPathToFile;
import static org.ckbk.sre.services.FileSystemService.initDirectory;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class RecipeServiceTest {

    private static Nitrite database;

    public static final String NAME = "Chicken Soup";
    public static final String AUTHOR = "MIHA84";
    public static final String COMPLEXITY = "3";
    public static final String TIME = "80";
    public static final String IMAGE = "images/recipes/recipe_2.jpg";
    public static final String DESCRIPTION = "Good Luck!";
    public static final String TYPE = "Lunch";
    public static final ArrayList<String> INGREDIENTS = new ArrayList<>();
    public static final ArrayList<Integer> QUANTITY = new ArrayList<>();


    public static final String USERNAME = "MIHA84";

    @BeforeAll
    static void beforeAll() {
        System.out.println("RecipeService testing");
        INGREDIENTS.add("Carrot");
        INGREDIENTS.add("Pasta");
        INGREDIENTS.add("Chicken broth");
        INGREDIENTS.add("Vegetables");
        QUANTITY.add(5);
        QUANTITY.add(250);
        QUANTITY.add(500);
        QUANTITY.add(10);
    }

    @AfterAll
    static void afterAll() {
        System.out.println("RecipeService testing");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.setApplicationFolder(".testing_blog");
        initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        database = Nitrite.builder()
                .filePath(getPathToFile("testing.db").toFile())
                .openOrCreate("test", "test");
        RecipeService.initDatabase(database);
        UserService.initDatabase(database);
    }

    @AfterEach
    void tearDown() {
        database.close();
    }

    @Test
    @DisplayName("Database is initialized and there are no recipes")
    void testDatabaseIsInitializedAndNoRecipeIsPersisted() {
        assertThat(RecipeService.getRecipeRepositorySize()).isEqualTo(0);
    }

    @Test
    @DisplayName("Recipe is successfully persisted to Database")
    void testRecipeIsAddedToDatabase() throws TimeIsNotPositiveException, InvalidImagePathException, NumberIsNotBetweenConstrainsException, EmptyInputFieldException, InputNotANumberException, UsernameAlreadyExistsException, EmailAddressIsNotValidException, PhoneNumberIsNotValidException {
        assertThat(RecipeService.getRecipeRepositorySize()).isEqualTo(0);
        UserService.addUser(USERNAME, "a", "mail@mail.com", "0123654789", "Smith", "John", "Client");
        assertThrows(InputNotANumberException.class, () ->
                RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME + "a", TYPE, IMAGE, DESCRIPTION));
        assertThrows(InputNotANumberException.class, () ->
                RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY + "a", TIME + "1", TYPE, IMAGE, DESCRIPTION));
        assertThrows(NumberIsNotBetweenConstrainsException.class, () ->
                RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), "6", TIME, TYPE, IMAGE, DESCRIPTION));
        assertThrows(NumberIsNotBetweenConstrainsException.class, () ->
                RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), "-1", TIME, TYPE, IMAGE, DESCRIPTION));
        assertThrows(TimeIsNotPositiveException.class, () ->
                RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, "-1", TYPE, IMAGE, DESCRIPTION));
        assertThrows(InvalidImagePathException.class, () ->
                RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME, TYPE, "something?else", DESCRIPTION));
        RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);
        assertThat(RecipeService.getRecipeRepositorySize()).isEqualTo(1);
        Recipe recipe = RecipeService.getRecipe(0);
        assertThat(recipe).isNotNull();
        assertThat(recipe.getName()).isEqualTo(NAME);
        assertThat(String.valueOf(recipe.getType())).isEqualTo(TYPE);
        assertThat(recipe.getAuthor()).isEqualTo(AUTHOR);
        assertThat(String.valueOf(recipe.getComplexity())).isEqualTo(COMPLEXITY);
        assertThat(recipe.getImage()).isEqualTo(IMAGE);
        assertThat(String.valueOf(recipe.getTime())).isEqualTo(TIME);
        assertThat(recipe.getDescription()).isEqualTo(DESCRIPTION);
        recipe.setIngredientName(INGREDIENTS);
        recipe.setIngredientQuantity(QUANTITY);
        assertThat(recipe.getIngredientName()).isEqualTo(INGREDIENTS);
        assertThat(recipe.getIngredientQuantity()).isEqualTo(QUANTITY);
        assertThat(recipe.isApproved()).isEqualTo(false);
    }

    @Test
    @DisplayName("Multiple recipes are successfully persisted to Database")
    void testRecipesAreAddedToDatabase() throws TimeIsNotPositiveException, InvalidImagePathException, NumberIsNotBetweenConstrainsException, EmptyInputFieldException, InputNotANumberException, UsernameAlreadyExistsException, EmailAddressIsNotValidException, PhoneNumberIsNotValidException {
        UserService.addUser(USERNAME, "a", "mail@mail.com", "0123654789", "Smith", "John", "Client");
        for(int i = 0; i < 50; i++)
            RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);
        assertThat(RecipeService.getRecipeRepository()).isNotNull();
        assertThat(RecipeService.getRecipeRepositorySize()).isEqualTo(50);
        for(Recipe recipe : RecipeService.getRecipeRepository().find().toList()){
            assertThat(recipe).isNotNull();
            assertThat(recipe.getName()).isEqualTo(NAME);
            assertThat(String.valueOf(recipe.getType())).isEqualTo(TYPE);
            assertThat(recipe.getAuthor()).isEqualTo(AUTHOR);
            assertThat(String.valueOf(recipe.getComplexity())).isEqualTo(COMPLEXITY);
            assertThat(recipe.getImage()).isEqualTo(IMAGE);
            assertThat(String.valueOf(recipe.getTime())).isEqualTo(TIME);
            assertThat(recipe.getDescription()).isEqualTo(DESCRIPTION);
            recipe.setIngredientName(INGREDIENTS);
            recipe.setIngredientQuantity(QUANTITY);
            assertThat(recipe.getIngredientName()).isEqualTo(INGREDIENTS);
            assertThat(recipe.getIngredientQuantity()).isEqualTo(QUANTITY);
            assertThat(recipe.isApproved()).isEqualTo(false);
        }
    }

    @Test
    @DisplayName("User's recipes are successfully read")
    void testMyRecipesAreReturned() throws TimeIsNotPositiveException, InvalidImagePathException, NumberIsNotBetweenConstrainsException, EmptyInputFieldException, InputNotANumberException, UsernameAlreadyExistsException, EmailAddressIsNotValidException, PhoneNumberIsNotValidException, InvalidCredentialsException {
        UserService.addUser(USERNAME, "a", "mail@mail.com", "0123654789", "Smith", "John", "Client");
        UserService.addUser(USERNAME + 1, "a", "mail@mail.com", "0123654789", "Smith", "John", "Client");
        UserService.logInUser(USERNAME, "a");

        RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);
        RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);
        RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR + 1), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);
        assertThat(RecipeService.getRecipeRepository()).isNotNull();
        assertThat(RecipeService.getRecipeRepositorySize()).isEqualTo(3);

        assertThat(RecipeService.getMyRecipes()).isNotNull();
        assertThat(RecipeService.getMyRecipes().size()).isEqualTo(2);
        assertThat(RecipeService.getRecipe(4)).isNull();
    }

    @Test
    @DisplayName("Recipe information is successfully updated")
    void testEditRecipe() throws TimeIsNotPositiveException, InvalidImagePathException, NumberIsNotBetweenConstrainsException, EmptyInputFieldException, InputNotANumberException, UsernameAlreadyExistsException, EmailAddressIsNotValidException, PhoneNumberIsNotValidException {
        UserService.addUser(USERNAME, "a", "mail@mail.com", "0123654789", "Smith", "John", "Client");
        RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);
        assertThat(RecipeService.getRecipeRepositorySize()).isEqualTo(1);
        Recipe recipe = RecipeService.getRecipe(0);
        assertThat(recipe).isNotNull();
        assertThat(recipe.getAuthor()).isEqualTo(AUTHOR);

        RecipeService.changeAnonymous(UserService.findUser(USERNAME));

        assertThat(recipe).isNotNull();
        assertThat(recipe.getName()).isEqualTo(NAME);
        assertThat(String.valueOf(recipe.getType())).isEqualTo(TYPE);
        assertThat(recipe.getAuthor()).isEqualTo(USERNAME);
        assertThat(String.valueOf(recipe.getComplexity())).isEqualTo(COMPLEXITY);
        assertThat(recipe.getImage()).isEqualTo(IMAGE);
        assertThat(String.valueOf(recipe.getTime())).isEqualTo(TIME);
        assertThat(recipe.getDescription()).isEqualTo(DESCRIPTION);
        recipe.setIngredientName(INGREDIENTS);
        recipe.setIngredientQuantity(QUANTITY);
        assertThat(recipe.getIngredientName()).isEqualTo(INGREDIENTS);
        assertThat(recipe.getIngredientQuantity()).isEqualTo(QUANTITY);
        assertThat(recipe.isApproved()).isEqualTo(false);
    }
}
