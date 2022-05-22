package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ckbk.sre.model.Order;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.services.OrderService;
import org.ckbk.sre.services.RecipeService;
import org.ckbk.sre.services.UserService;

import java.io.IOException;

public class OrderController {
    @FXML
    private Text ingredient;
    @FXML
    private Text quantity;
    @FXML
    private Text username;
    @FXML
    private Text shop;
    @FXML
    private Text price;
    @FXML
    private Text date;
    @FXML
    private Text totalPrice;
    @FXML
    private Button closeButton;

    private static int index;
    public static void setIndex(int i){ index = i;}

    @FXML
    public void initialize(){
        load();
    }

    @FXML
    public void load(){
        Order order = OrderService.getOrderRepository().find().toList().get(index);
        username.setText("Buyer: " + order.getUsername());
        shop.setText("Shop: " + order.getShop());
        date.setText("Date: " + order.getDate());
        totalPrice.setText("Total: " + String.format("%.2f", order.getTotalPrice()) + " RON");
        ingredient.setText("Product\n\n" + order.getIngredientNameString());
        quantity.setText("Quantity\n\n" + order.getIngredientQuantityString());
        price.setText("Price\n\n" + order.getIngredientPriceString());
    }

    @FXML
    public void handleCloseOrder() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
