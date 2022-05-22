package org.ckbk.sre.services;

import org.ckbk.sre.exceptions.*;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class RecipeService {

    private static ObjectRepository<Recipe> recipeRepository;

    public static void initDatabase(Nitrite database) {
        recipeRepository = database.getRepository(Recipe.class);
    }

    public static void addRecipe(String name, User author, String complexity, String time, String type, String image, String description) throws EmptyInputFieldException, InputNotANumberException, NumberIsNotBetweenConstrainsException, InvalidImagePathException, TimeIsNotPositiveException {
        checkInputFieldsAreFilled(name, complexity, time, image, description);
        checkInputIsANumber(complexity, time);
        checkNumberIsBetweenConstrains(complexity);
        checkTimeIsPositive(time);
        checkValidPath(image);
        switch (type) {
            case "Breakfast":
                recipeRepository.insert(new Recipe(name, author.getUsername(), Integer.parseInt(complexity), Integer.parseInt(time), image, description, Recipe.TYPE.Breakfast));
                break;
            case "Lunch":
                recipeRepository.insert(new Recipe(name, author.getUsername(), Integer.parseInt(complexity), Integer.parseInt(time), image, description, Recipe.TYPE.Lunch));
                break;
            case "Dinner":
                recipeRepository.insert(new Recipe(name, author.getUsername(), Integer.parseInt(complexity), Integer.parseInt(time), image, description, Recipe.TYPE.Dinner));
                break;
            default:
                recipeRepository.insert(new Recipe(name, author.getUsername(), Integer.parseInt(complexity), Integer.parseInt(time), image, description, Recipe.TYPE.Other));
                break;
        }
    }

    private static void checkTimeIsPositive(String time) throws TimeIsNotPositiveException{
        if(Integer.parseInt(time) <= 0)
            throw new TimeIsNotPositiveException();
    }

    private static void checkInputFieldsAreFilled(String name, String complexity, String time, String image, String description) throws EmptyInputFieldException {
        if(name.isEmpty() || image.isEmpty() || description.isEmpty() || complexity.isEmpty() || time.isEmpty()) throw new EmptyInputFieldException();
    }

    public static ObjectRepository<Recipe> getRecipeRepository() {
        return recipeRepository;
    }

    private static void checkInputIsANumber(String complexity, String time) throws InputNotANumberException {
        try {
            Integer.parseInt(complexity);
            Integer.parseInt(time);
        } catch (NumberFormatException nfe) {
            throw new InputNotANumberException();
        }
    }

    public static void checkValidPath(String path) throws InvalidImagePathException{
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            throw new InvalidImagePathException();
        }
    }

    private static void checkNumberIsBetweenConstrains(String complexity) throws NumberIsNotBetweenConstrainsException {
        if(Integer.parseInt(complexity) < 0 || Integer.parseInt(complexity) > 5)
            throw new NumberIsNotBetweenConstrainsException();
    }

    public static Recipe getRecipe(int i){
        try{
            return recipeRepository.find().toList().get(i);
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public static void changeAnonymous(User user){
        for (Recipe r : recipeRepository.find())
            if(Objects.equals(r.getAuthor(), user.getUsername())){
                r.setAuthor("admin");
                recipeRepository.update(r);
            }
    }

    public static ArrayList<Integer> getMyRecipes(){
        ArrayList<Integer> mine = new ArrayList<>();
        var l = recipeRepository.find().toList();
        for(Recipe r : l){
            if(Objects.equals(r.getAuthor(), UserService.getUser().getUsername())) {
                mine.add(l.indexOf(r));
            }
        }
        return mine;
    }

    public static long getRecipeRepositorySize(){
        return recipeRepository.size();
    }
}
