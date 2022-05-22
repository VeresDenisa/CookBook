package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.ckbk.sre.Main;
import org.ckbk.sre.services.OrderService;

import java.io.IOException;
import java.util.ArrayList;

public class MyManagerProfileController {
    @FXML
    private Pane pane;
    @FXML
    private Button prev;
    @FXML
    private Button next;

    private int nrPage;

    @FXML
    public void initialize(){
        nrPage = 1;
        reloadPages();
    }

    public void reloadPages() {
        while (pane.getChildren().size() != 0) {
            pane.getChildren().remove(0);
        }

        next.setVisible(true);
        prev.setVisible(true);

        if (nrPage == 1) {
            prev.setVisible(false);
        }
        ArrayList<Integer> list;
        list = OrderService.getMySentOrders();

        if (nrPage == ((list.size() - 1) / 3 + 1) || list.isEmpty()) {
            next.setVisible(false);
        }

        if (!(list.isEmpty())) {
            for (int i = 0; i < 8; i++) {
                if ((nrPage - 1) * 8 + i >= list.size()) continue;

                FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("OrderBox.fxml"));

                try {
                    Pane p = loader.load();
                    ((OrderBoxController) (loader.getController())).load(list.get((nrPage - 1) * 8 + i));
                    pane.getChildren().add(p);
                    if(i < 4) p.setLayoutY(20);
                    else p.setLayoutY(205);
                    p.setLayoutX(40 + (i % 4) * 175);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (nrPage == (OrderService.getOrderRepositorySize() - 1) / 3 + 1) {
                next.setVisible(false);
            }
        }
    }

    @FXML
    public void handleGoNext(){
        nrPage++;
        reloadPages();
    }

    @FXML
    public void handleGoPrev(){
        nrPage--;
        reloadPages();
    }

    public void handleGoOrders() {
        nrPage = 1;
        reloadPages();
    }

    public void handleGoStatistics() throws IOException {
        org.ckbk.sre.Main.primaryStage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MyManagerStatistics.fxml"));
        primaryStage.setTitle("MY PROFILE");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        org.ckbk.sre.Main.primaryStage = primaryStage;
    }
}
