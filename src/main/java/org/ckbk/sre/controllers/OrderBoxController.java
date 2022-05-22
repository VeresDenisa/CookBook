package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ckbk.sre.model.Order;
import org.ckbk.sre.services.OrderService;

import java.io.IOException;

public class OrderBoxController {
    @FXML
    private Text sent;
    @FXML
    private Text username;
    @FXML
    private Text totalPrice;
    @FXML
    private Text date;
    @FXML
    private Text productNumber;

    private int index;

    @FXML
    public void load(int i){
        Order order = OrderService.getOrder(i);
        this.index = i;
        username.setText("Buyer: " + order.getUsername());
        date.setText("Date: " + order.getDate());
        if(order.isSent()) sent.setText("Complete");
        else sent.setText("Incomplete");
        totalPrice.setText("Total: " + order.getTotalPrice() + " RON");
        productNumber.setText("Number of products: " + order.getIngredientsNumber());
    }

    @FXML
    public void handleOpenAction() throws IOException {
        OrderController.setIndex(this.index);
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Order.fxml"));
        primaryStage.setTitle("ORDER");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
