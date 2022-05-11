package org.ckbk.sre.services;

import org.ckbk.sre.model.RecipeList;
import org.ckbk.sre.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.Objects;

public class RecipeListService {

    private static ObjectRepository<RecipeList> recipeListRepository;

    public static void initDatabase(Nitrite database) {
        recipeListRepository = database.getRepository(RecipeList.class);
    }

    public static void addRecipeList(String username, int recipeId, String state, boolean fav) {
        boolean insert = true;
        for (RecipeList recipeList : recipeListRepository.find()) {
            if (Objects.equals(username, recipeList.getUsername()) && Objects.equals(recipeId, recipeList.getRecipeId())) {
                switch (state) {
                    case "done":
                        recipeListRepository.update(new RecipeList(username, recipeId, fav, false, false, true));
                    case "toDo":
                        recipeListRepository.update(new RecipeList(username, recipeId, fav, false, true, false));
                    case "now":
                        recipeListRepository.update(new RecipeList(username, recipeId, fav, true, false, false));
                }
                insert = false;
            }
        }
        if(insert)
            switch (state){
                case "done":
                    recipeListRepository.insert(new RecipeList(username, recipeId, fav, false, false, true));
                case "toDo":
                    recipeListRepository.insert(new RecipeList(username, recipeId, fav, false,true, false));
                case "now":
                    recipeListRepository.insert(new RecipeList(username, recipeId, fav, true, false, false));
            }

    }
}
