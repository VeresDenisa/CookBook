package org.ckbk.sre.services;

import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import static org.ckbk.sre.services.FileSystemService.getPathToFile;

public class RecipeService {

    private static ObjectRepository<Recipe> recipeRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("UserFile.db").toFile())
                .openOrCreate("test", "test");

        recipeRepository = database.getRepository(Recipe.class);
    }

    public static void addRecipe(String name, User author, int complexity, int time, Recipe.TYPE type, String image, String description) throws EmptyInputFieldException {
        checkInputFieldsAreFilled(name, author, complexity, time, image, description);
        recipeRepository.insert(new Recipe(name, author, complexity, time, image, description, type));
    }

    private static void checkInputFieldsAreFilled(String name, User author, int complexity, int time, String image, String description) throws EmptyInputFieldException {
        if(name.isEmpty() || author.getUsername().isEmpty() || image.isEmpty() || description.isEmpty()) throw new EmptyInputFieldException();
        if(!String.valueOf(complexity).matches("\\d+")) throw new EmptyInputFieldException();
        if(!String.valueOf(time).matches("\\d+")) throw new EmptyInputFieldException();
    }
}
