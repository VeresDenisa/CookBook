package org.ckbk.sre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ckbk.sre.model.Order;
import org.ckbk.sre.model.Product;
import org.ckbk.sre.services.*;
import org.dizitart.no2.Nitrite;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.ckbk.sre.services.FileSystemService.getPathToFile;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStageS) throws Exception {
        initDirectory();
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("CKBK.db").toFile())
                .openOrCreate("test", "test");
        UserService.initDatabase(database);
        RecipeService.initDatabase(database);
        RecipeListService.initDatabase(database);
        ProductService.initDatabase(database);
        OrderService.initDatabase(database);

        if(UserService.getUserRepositorySize() == 0){
            UserService.addUser("admin", "a", "dennisa_dnns@yahoo.com", "0756134164", "admin", "admin", "Admin");
            UserService.addUser("george", "a", "dennisa_dnns@yahoo.com", "0756134164", "admin", "admin", "Client");
            UserService.addUser("andy", "a", "dennisa_dnns@yahoo.com", "0756134164", "admin", "admin", "Client");
            UserService.addUser("marius", "a", "dennisa_dnns@yahoo.com", "0756134164", "admin", "admin", "Client");
            UserService.addUser("manager", "a", "dennisa_dnns@yahoo.com", "0756134164", "admin", "admin", "Manager");
            RecipeService.addRecipe("Grilled Pork Chops", UserService.findUser("andy"), "2", "120", "Lunch", "images/recipes/recipe_0.jpg", "It's easy to think you may not be a pork chop fan. They often come overcooked and dry, but they don't have to be that way. These pork chops are marinated to make them extra flavorful and tender. A marinade will also help a pork chop from being dry. The marinade works to break down the meat and keeps it tender and juicy after cooking. Pork chops can go in so many different flavor routes. Paprika rubbed, smothered, or like this one with a honey soy marinade. ");
            RecipeService.addRecipe("Pureed Broccoli Soup", UserService.findUser("andy"), "4", "40", "Lunch", "images/recipes/recipe_1.jpg", "Heat butter and oil in a Dutch oven over medium heat until the butter melts. Add onion and celery; cook, stirring occasionally, until softened, 4 to 6 minutes. Add garlic and thyme (or parsley); cook, stirring, until fragrant, about 10 seconds. Stir in broccoli. Add water and broth; bring to a lively simmer over high heat. Reduce heat to maintain a lively simmer and cook until very tender, about 8 minutes. Puree the soup in batches in a blender until smooth. (Use caution when pureeing hot liquids.) Stir in half-and-half (if using), salt and pepper.");
            RecipeService.addRecipe("Omelette", UserService.findUser("marius"), "1", "15", "Breakfast", "images/recipes/recipe_2.jpg", "Not only is an omelette quick and easy to make, it is a paragon of economy. Odds and ends (a.k.a. leftovers) rise to a new level when placed inside an omelette.");
            RecipeService.addRecipe("Boiled Eggs", UserService.findUser("andy"), "0", "10", "Breakfast", "images/recipes/recipe_3.jpg", "If you need easy-to-peel eggs and you have fresh eggs, you might want to try steaming the eggs for 15 minutes. Pour an inch of water into a pot and insert a steamer basket. Bring to a boil. Place the eggs in the steamer basket, cover, and steam for 15 minutes (more or less, check!). (Or if you don't have a steamer basket, steam the eggs in a half-inch of water.) The steam penetrates the shell a bit making the eggs easier to peel. (You can also steam eggs in a pressure cooker!)");
            RecipeService.addRecipe("Pudding", UserService.findUser("george"), "3", "70", "Other", "images/recipes/recipe_4.jpg", "In 2-quart saucepan, mix sugar, cornstarch and salt. Gradually stir in milk. Cook over medium heat, stirring constantly, until mixture thickens and boils. Boil and stir 1 minute. Gradually stir at least half of the hot mixture into egg yolks, then stir back into hot mixture in saucepan. Boil and stir 1 minute; remove from heat. Stir in butter and vanilla. Pour pudding into dessert dishes. Cover and refrigerate about 1 hour or until chilled. Store covered in refrigerator.");
            RecipeService.addRecipe("Grilled Chicken", UserService.findUser("andy"), "4", "100", "Lunch", "images/recipes/recipe_5.jpg", "Blend Spread with Italian seasoning in small bowl. Brush chicken and vegetables with seasoning mixture. Grill or broil chicken and vegetables, turning occasionally, until chicken is thoroughly cooked and vegetables are tender. Serve chicken and vegetables with hot Knorr® Rice Sides™ - Cheddar Broccoli.");
            RecipeService.addRecipe("French Potato Salad", UserService.findUser("marius"), "5", "120", "Lunch", "images/recipes/recipe_6.jpg", "1) Drop the white and red potatoes into a large pot of boiling salted water and cook for 20 to 30 minutes, until they are just cooked through. Drain in a colander and place a towel over the potatoes to allow them to steam for 10 more minutes. As soon as you can handle them, cut in half (quarters if the potatoes are larger) and place in a medium bowl. Toss gently with the wine and chicken stock. Allow the liquids to soak into the warm potatoes before proceeding. Combine the vinegar, mustard, 1/2 tsp salt and 1/4 tsp pepper and slowly whisk in the olive oil to make an emulsion. Add the vinaigrette to the potatoes. Add the spring onions, dill, parsley, basil, 1 1/2 tsp salt and 1/2 tsp pepper and toss. Serve warm or at room temperature.");
            ProductService.addProduct("Milk", "images/recipes/recipe_0.jpg", UserService.findUser("manager").getUsername(), "1", "5.24", "L", "20");
            ProductService.addProduct("Eggs", "images/recipes/recipe_0.jpg", UserService.findUser("manager").getUsername(), "10", "9.97", "10 pieces", "30");
            ProductService.addProduct("Chicken Breasts", "images/recipes/recipe_0.jpg", UserService.findUser("manager").getUsername(), "1", "12.55", "Kg", "4");
            ProductService.addProduct("Chocolate Bar", "images/recipes/recipe_0.jpg", UserService.findUser("manager").getUsername(), "1", "15.00", "piece", "2");
            ProductService.addProduct("Candy Bar", "images/recipes/recipe_0.jpg", UserService.findUser("manager").getUsername(), "1", "3.11", "piece", "0");
            ProductService.addProduct("Sprinkles", "images/recipes/recipe_0.jpg", UserService.findUser("manager").getUsername(), "1", "5.24", "L", "20");
            ProductService.addProduct("Natural Yogurt", "images/recipes/recipe_0.jpg", UserService.findUser("manager").getUsername(), "250", "6.24", "g", "1750");
            ProductService.addProduct("Strawberries", "images/recipes/recipe_0.jpg", UserService.findUser("manager").getUsername(), "500", "26.18", "g", "1500");
            ArrayList<Product> product = new ArrayList<Product>();
            ArrayList<Integer> quantity = new ArrayList<Integer>();
            product.add(ProductService.findProduct("manager", "Milk"));
            quantity.add(3);
            product.add(ProductService.findProduct("manager", "Eggs"));
            quantity.add(30);
            product.add(ProductService.findProduct("manager", "Strawberries"));
            quantity.add(500);
            product.add(ProductService.findProduct("manager", "Chicken Breasts"));
            quantity.add(2);
            OrderService.addOrder("andy", "manager", true, product, quantity);
        }

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SignUpIn.fxml"));
        primaryStageS.setTitle("SIGN IN / SIGN UP");
        primaryStageS.setScene(new Scene(root, 900, 500));
        primaryStageS.setResizable(false);
        primaryStageS.show();
        primaryStage = primaryStageS;
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
