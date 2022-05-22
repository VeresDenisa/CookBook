package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
            for (int i = 0; i < 4; i++) {
                if ((nrPage - 1) * 4 + i >= list.size()) continue;

                FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("OrderBox.fxml"));

                try {
                    Pane p = loader.load();
                    ((OrderBoxController) (loader.getController())).load(list.get((nrPage - 1) * 4 + i));
                    pane.getChildren().add(p);
                    p.setLayoutX(180);
                    p.setLayoutY(40 + i * 120);

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

    public void handleGoStatistics() {
        nrPage = 1;
        reloadPages();
    }
}
