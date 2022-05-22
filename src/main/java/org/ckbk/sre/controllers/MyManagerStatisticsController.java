package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.ckbk.sre.services.OrderService;

import java.io.IOException;

public class MyManagerStatisticsController {
    @FXML
    private LineChart chart;

    @FXML
    public void initialize(){
        XYChart.Series<String,Number> series = new XYChart.Series<>();

        for(int i = 0; i < OrderService.getMySentOrders().size(); i++)
            series.getData().add(new XYChart.Data<>(OrderService.getOrder(OrderService.getMySentOrders().get(i)).getDate(), OrderService.getOrder(OrderService.getMySentOrders().get(i)).getTotalPrice()));

        chart.setLegendVisible(false);
        chart.getData().clear();
        chart.getData().add(series);
    }

    public void handleGoOrders() throws IOException {
        org.ckbk.sre.Main.primaryStage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MyManagerProfile.fxml"));
        primaryStage.setTitle("MY PROFILE");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        org.ckbk.sre.Main.primaryStage = primaryStage;
    }
}
