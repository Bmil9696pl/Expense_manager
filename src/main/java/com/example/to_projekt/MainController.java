package com.example.to_projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MainController extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            VBox root =
                    loader.load(getClass().getResource("/mainView.fxml").openStream());
            MainView mainView = loader.getController();
            mainView.setNumerMiesiaca(LocalDate.now().getMonthValue()-1);
            mainView.getTextMiesiac().setText(mainView.getMiesiace()[mainView.getNumerMiesiaca()]);
            mainView.initialize();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}