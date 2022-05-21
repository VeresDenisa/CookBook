package org.ckbk.sre.services;

import org.ckbk.sre.model.RecipeList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

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
                recipeList.setFav(fav);
                recipeList.setToDo(toDo);
                recipeList.setNow(now);
                recipeList.setDone(done);
                return;
            }
        }
        recipeListRepository.insert(new RecipeList(username, recipeId, fav,toDo, now, done));
        System.out.println(recipeListRepository.find().toList().get(recipeListRepository.find().toList().size() - 1));
    }

    public static ArrayList<Integer> getMyFavRecipes(){
        ArrayList<Integer> fav = new ArrayList<Integer>();
        for(RecipeList rl: recipeListRepository.find()){
            if(Objects.equals(UserService.getUser().getUsername(), rl.getUsername()) && rl.isFav()){
                fav.add(rl.getRecipeId());
            }
        }
        return fav;
    }

    public static ArrayList<Integer> getMyToDoRecipes(){
        ArrayList<Integer> toDo = new ArrayList<Integer>();
        for(RecipeList rl: recipeListRepository.find()){
            if(Objects.equals(UserService.getUser().getUsername(), rl.getUsername()) && rl.isToDo()){
                toDo.add(rl.getRecipeId());
            }
        }
        return toDo;
    }

    public static ArrayList<Integer> getMyDoneRecipes(){
        ArrayList<Integer> done = new ArrayList<Integer>();
        for(RecipeList rl: recipeListRepository.find()){
            if(Objects.equals(UserService.getUser().getUsername(), rl.getUsername()) && rl.isToDo()){
                done.add(rl.getRecipeId());
            }
        }
        return done;
    }

    public static void showRecipesList(){
        for(RecipeList rl : recipeListRepository.find())
            System.out.println(rl.getUsername() + "  " + rl.getUsername());
    }
}
