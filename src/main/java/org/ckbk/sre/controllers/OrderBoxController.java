package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.ckbk.sre.model.Order;
import org.ckbk.sre.services.OrderService;

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
    public void handleOpenAction() {
    }
}
