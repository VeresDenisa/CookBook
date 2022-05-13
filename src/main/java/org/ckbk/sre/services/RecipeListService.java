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

    public static void addRecipeList(String username, int recipeId, boolean toDo, boolean now, boolean done, boolean fav) {
        for (RecipeList recipeList : recipeListRepository.find()) {
            if (Objects.equals(username, recipeList.getUsername()) && Objects.equals(recipeId, recipeList.getRecipeId())) {
                if(fav != recipeList.isFav())
                    recipeList.setFav(fav);
                if(toDo != recipeList.isToDo())
                    recipeList.setToDo(toDo);
                if(now != recipeList.isNow())
                    recipeList.setNow(now);
                if(done != recipeList.isDone())
                    recipeList.setDone(done);
                return;
            }
        }
        recipeListRepository.insert(new RecipeList(username, recipeId, fav,toDo, now, done));
    }
}
