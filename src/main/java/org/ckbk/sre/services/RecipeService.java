package org.ckbk.sre.services;

import javafx.scene.control.ChoiceBox;
import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.Objects;

import static org.ckbk.sre.services.FileSystemService.getPathToFile;

public class RecipeService {

    private static ObjectRepository<Recipe> recipeRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("UserFile.db").toFile())
                .openOrCreate("test", "test");

        recipeRepository = database.getRepository(Recipe.class);
    }

    public static void addRecipe(String name, String author, int complexity, int time, String type, String image, String description) throws EmptyInputFieldException {
        checkInputFieldsAreFilled(name, complexity, time, image, description);
        Recipe.TYPE typeT;
        if(type == "Breakfast")  typeT = Recipe.TYPE.Breakfast;
        else if(type == "Lunch") typeT = Recipe.TYPE.Lunch;
        else if(type == "Dinner") typeT = Recipe.TYPE.Dinner;
        else typeT = Recipe.TYPE.Other;
        recipeRepository.insert(new Recipe(name, author, complexity, time, image, description, typeT));
    }

    private static void checkInputFieldsAreFilled(String name, int complexity, int time, String image, String description) throws EmptyInputFieldException {
        if(name.isEmpty() || image.isEmpty() || description.isEmpty()) throw new EmptyInputFieldException();
        if(!String.valueOf(complexity).matches("\\d+")) throw new EmptyInputFieldException();
        if(!String.valueOf(time).matches("\\d+")) throw new EmptyInputFieldException();
    }
}
