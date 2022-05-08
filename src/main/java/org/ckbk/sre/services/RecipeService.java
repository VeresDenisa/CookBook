package org.ckbk.sre.services;

import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.ckbk.sre.exceptions.InputNotANumberException;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

public class RecipeService {

    private static ObjectRepository<Recipe> recipeRepository;

    public static void initDatabase(Nitrite database) {
        recipeRepository = database.getRepository(Recipe.class);
    }

    public static void addRecipe(String name, User author, String complexity, String time, String type, String image, String description) throws EmptyInputFieldException, InputNotANumberException {
        checkInputFieldsAreFilled(name, complexity, time, image, description);
        checkInputIsANumber(complexity, time);
        Recipe.TYPE typeT;
        switch (type) {
            case "Breakfast":
                typeT = Recipe.TYPE.Breakfast;
            case "Lunch":
                typeT = Recipe.TYPE.Lunch;
            case "Dinner":
                typeT = Recipe.TYPE.Dinner;
            default:
                typeT = Recipe.TYPE.Other;
        }
        recipeRepository.insert(new Recipe(name, author, Integer.parseInt(complexity), Integer.parseInt(time), image, description, typeT));
    }

    private static void checkInputFieldsAreFilled(String name, String complexity, String time, String image, String description) throws EmptyInputFieldException {
        if(name.isEmpty() || image.isEmpty() || description.isEmpty() || complexity.isEmpty() || time.isEmpty()) throw new EmptyInputFieldException();
    }

    private static void checkInputIsANumber(String complexity, String time) throws InputNotANumberException {
        try {
            Integer.parseInt(complexity);
            Integer.parseInt(time);
        } catch (NumberFormatException nfe) {
            throw new InputNotANumberException();
        }
    }
}
