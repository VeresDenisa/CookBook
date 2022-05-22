package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.ckbk.sre.Main;
import org.ckbk.sre.services.UserService;

import java.io.IOException;
import java.util.ArrayList;

public class MyAdminProfileController {
    @FXML
    private Pane pane;
    @FXML
    private Button prev;
    @FXML
    private Button next;

    private int nrPage;
    private int current;

    @FXML
    public void initialize(){
        nrPage = 1;
        current = 1;
        reloadPages();
    }

    public void reloadPages(){
        while (pane.getChildren().size() != 0) {
            pane.getChildren().remove(0);
        }
        next.setVisible(true);
        prev.setVisible(true);

        if (nrPage == 1) {
            prev.setVisible(false);
        }

        ArrayList<String> list;
        if(current == 1)
            list = UserService.getReportedUsers();
        else
            list = UserService.getReportedUsers();

        assert list != null;
        if (nrPage == ((list.size() - 1) / 8 + 1) || list.isEmpty()) {
            next.setVisible(false);
        }

        if(!(list.isEmpty())) {
            for (int i = 0; i < 8; i++) {
                if ((nrPage - 1) * 3 + i >= list.size()) continue;
                FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("UserBox.fxml"));

                try {
                    Pane p = loader.load();
                    ((UserBoxController) (loader.getController())).load(UserService.findUser(list.get((nrPage - 1) * 3 + i)));
                    pane.getChildren().add(p);
                    if(i % 2 == 0) p.setLayoutX(20);
                    else p.setLayoutX(420);
                    p.setLayoutY(20 + (i / 2) * 90);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void handleGoUsers() {
        nrPage = 1;
        current = 2;
        reloadPages();
    }

    @FXML
    public void handleGoRecipes() {
        nrPage = 1;
        current = 1;
        reloadPages();
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
}
