package org.ckbk.sre.services;

import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.ckbk.sre.exceptions.InputNotANumberException;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.model.RecipeList;
import org.ckbk.sre.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.Objects;

public class RecipeService {

    private static ObjectRepository<Recipe> recipeRepository;

    public static void initDatabase(Nitrite database) {
        recipeRepository = database.getRepository(Recipe.class);
    }

    public static void addRecipe(String name, User author, String complexity, String time, String type, String image, String description) throws EmptyInputFieldException, InputNotANumberException {
        checkInputFieldsAreFilled(name, complexity, time, image, description);
        checkInputIsANumber(complexity, time);
        switch (type) {
            case "Breakfast":
                recipeRepository.insert(new Recipe(name, author.getUsername(), Integer.parseInt(complexity), Integer.parseInt(time), image, description, Recipe.TYPE.Breakfast));
            case "Lunch":
                recipeRepository.insert(new Recipe(name, author.getUsername(), Integer.parseInt(complexity), Integer.parseInt(time), image, description, Recipe.TYPE.Lunch));
            case "Dinner":
                recipeRepository.insert(new Recipe(name, author.getUsername(), Integer.parseInt(complexity), Integer.parseInt(time), image, description, Recipe.TYPE.Dinner));
            default:
                recipeRepository.insert(new Recipe(name, author.getUsername(), Integer.parseInt(complexity), Integer.parseInt(time), image, description, Recipe.TYPE.Other));
        }
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

    public static Recipe getRecipe(long i){
        for (Recipe recipe : recipeRepository.find()) {
            if(recipe.getRecipeId() == i)
                return recipe;
        }
        return null;
    }

    public static long getRecipeRepositorySize(){
        return recipeRepository.size();
    }
}
