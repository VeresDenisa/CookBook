package org.ckbk.sre;

import org.apache.commons.io.FileUtils;
import org.ckbk.sre.exceptions.*;
import org.ckbk.sre.model.RecipeList;
import org.ckbk.sre.services.*;
import org.ckbk.sre.services.RecipeService;
import org.dizitart.no2.Nitrite;
import org.junit.jupiter.api.*;

import static org.ckbk.sre.services.FileSystemService.getPathToFile;
import static org.ckbk.sre.services.FileSystemService.initDirectory;
import static org.testfx.assertions.api.Assertions.assertThat;

class RecipeListServiceTest {

    private static Nitrite database;

    public static final String NAME = "Chicken Soup";
    public static final String AUTHOR = "MIHA84";
    public static final String COMPLEXITY = "3";
    public static final String TIME = "80";
    public static final String IMAGE = "images/recipes/recipe_2.jpg";
    public static final String DESCRIPTION = "Good Luck!";
    public static final String TYPE = "Lunch";

    public static final String USERNAME = "MIHA84";
    public static final int RECIPEID = 0;
    public static final boolean TODO = false;
    public static final boolean DONE = true;
    public static final boolean FAV = false;
    public static final boolean NOW = false;

    @BeforeAll
    static void beforeAll() {
        System.out.println("RecipeListService testing");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("RecipeListService testing");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.setApplicationFolder(".testing_blog");
        initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        database = Nitrite.builder()
                .filePath(getPathToFile("testing.db").toFile())
                .openOrCreate("test", "test");
        RecipeListService.initDatabase(database);
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
        assertThat(RecipeListService.getRecipesListRepository().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("RecipeList is successfully persisted to Database")
    void testRecipeIsAddedToDatabase() throws TimeIsNotPositiveException, InvalidImagePathException, NumberIsNotBetweenConstrainsException, EmptyInputFieldException, InputNotANumberException, UsernameAlreadyExistsException, EmailAddressIsNotValidException, PhoneNumberIsNotValidException {
        assertThat(RecipeListService.getRecipesListRepository().size()).isEqualTo(0);
        UserService.addUser(USERNAME, "a", "mail@mail.com", "0123654789", "Smith", "John", "Client");
        RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);
        assertThat(RecipeListService.getRecipesListRepository().size()).isEqualTo(0);

        RecipeListService.addRecipeList(USERNAME, RECIPEID, TODO,NOW,DONE, FAV);
        assertThat(RecipeListService.getRecipesListRepository().size()).isEqualTo(1);

        RecipeList recipeList = RecipeListService.getRecipesListRepository().find().toList().get(0);        assertThat(recipeList).isNotNull();
        assertThat(recipeList.getUsername()).isEqualTo(USERNAME);
        assertThat(recipeList.getRecipeId()).isEqualTo(RECIPEID);
        assertThat(recipeList.isDone()).isEqualTo(true);
        assertThat(recipeList.isToDo()).isEqualTo(false);
        assertThat(recipeList.isNow()).isEqualTo(false);
        assertThat(recipeList.isFav()).isEqualTo(false);

        recipeList.setFav(true);
        assertThat(recipeList.isDone()).isEqualTo(true);
        assertThat(recipeList.isToDo()).isEqualTo(false);
        assertThat(recipeList.isNow()).isEqualTo(false);
        assertThat(recipeList.isFav()).isEqualTo(true);

        recipeList.setNow(true);
        assertThat(recipeList.isDone()).isEqualTo(false);
        assertThat(recipeList.isToDo()).isEqualTo(false);
        assertThat(recipeList.isNow()).isEqualTo(true);
        assertThat(recipeList.isFav()).isEqualTo(true);

        recipeList.setNow(true);
        assertThat(recipeList.isDone()).isEqualTo(false);
        assertThat(recipeList.isToDo()).isEqualTo(false);
        assertThat(recipeList.isNow()).isEqualTo(true);
        assertThat(recipeList.isFav()).isEqualTo(true);
    }

    @Test
    @DisplayName("Multiple RecipeLists successfully added to database and respective lists")
    void testMultipleRecipeListsAreAddedToList() throws TimeIsNotPositiveException, InvalidImagePathException, NumberIsNotBetweenConstrainsException, EmptyInputFieldException, InputNotANumberException, UsernameAlreadyExistsException, EmailAddressIsNotValidException, PhoneNumberIsNotValidException, InvalidCredentialsException {
        UserService.addUser(USERNAME, "a", "mail@mail.com", "0123654789", "Smith", "John", "Client");
        UserService.addUser(USERNAME + 1, "a", "mail@mail.com", "0123654789", "Smith", "John", "Client");
        UserService.logInUser(USERNAME, "a");

        RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);
        RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);
        RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);
        RecipeService.addRecipe(NAME, UserService.findUser(AUTHOR + 1), COMPLEXITY, TIME, TYPE, IMAGE, DESCRIPTION);

        RecipeListService.addRecipeList(USERNAME, RECIPEID, TODO,NOW,DONE, FAV);
        RecipeListService.addRecipeList(USERNAME + 1, RECIPEID, TODO,NOW,DONE, true);
        RecipeListService.addRecipeList(USERNAME, RECIPEID + 1, TODO,true,false, FAV);
        RecipeListService.addRecipeList(USERNAME, RECIPEID + 2, TODO,NOW,false, true);
        RecipeListService.addRecipeList(USERNAME, RECIPEID + 2, true,NOW,false, FAV);
        assertThat(RecipeListService.getRecipesListRepository().size()).isEqualTo(4);

        assertThat(RecipeListService.getMyDoneRecipes().size()).isEqualTo(1);
        assertThat(RecipeListService.getMyFavRecipes().size()).isEqualTo(1);
        assertThat(RecipeListService.getMyToDoRecipes().size()).isEqualTo(1);
    }
}
