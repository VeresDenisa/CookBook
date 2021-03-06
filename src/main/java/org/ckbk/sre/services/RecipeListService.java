package org.ckbk.sre.services;

import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.model.RecipeList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.h2.store.fs.FileUtils;

import java.util.ArrayList;
import java.util.Objects;

public class RecipeListService {

    private static ObjectRepository<RecipeList> recipeListRepository;

    public static void initDatabase(Nitrite database) {
        recipeListRepository = database.getRepository(RecipeList.class);
    }

    public static void addRecipeList(String username, int recipeId, boolean toDo, boolean now, boolean done, boolean fav) {
        for (RecipeList recipeList : recipeListRepository.find()) {
            if (Objects.equals(username, recipeList.getUsername()) && Objects.equals(recipeId, recipeList.getRecipeId())) {
                if(fav) recipeList.setFav(true);
                else{
                    recipeList.setToDo(toDo);
                    recipeList.setNow(now);
                    recipeList.setDone(done);
                }
                recipeListRepository.update(recipeList);
                return;
            }
        }
        recipeListRepository.insert(new RecipeList(username, recipeId, fav,toDo, now, done));
    }

    public static ArrayList<Integer> getMyFavRecipes(){
        ArrayList<Integer> mine = new ArrayList<>();
        var l = recipeListRepository.find().toList();
        for(RecipeList r : l){
            if(Objects.equals(UserService.getUser().getUsername(), r.getUsername()) && r.isFav()) {
                mine.add(r.getRecipeId());
            }
        }
        return mine;
    }

    public static ArrayList<Integer> getMyToDoRecipes(){
        ArrayList<Integer> mine = new ArrayList<>();
        var l = recipeListRepository.find().toList();
        for(RecipeList r : l){
            if(Objects.equals(UserService.getUser().getUsername(), r.getUsername()) && r.isToDo()) {
                mine.add(r.getRecipeId());
            }
        }
        return mine;
    }

    public static ArrayList<Integer> getMyDoneRecipes(){
        ArrayList<Integer> mine = new ArrayList<>();
        var l = recipeListRepository.find().toList();
        for(RecipeList r : l){
            if(Objects.equals(UserService.getUser().getUsername(), r.getUsername()) && r.isDone()) {
                mine.add(r.getRecipeId());
            }
        }
        return mine;
    }

    public static ObjectRepository<RecipeList> getRecipesListRepository() {
        return recipeListRepository;
    }
}
