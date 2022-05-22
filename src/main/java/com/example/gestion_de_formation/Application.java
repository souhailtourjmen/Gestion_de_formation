package com.example.gestion_de_formation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        try {
            stage.getIcons().add(new Image(this.getClass().getResource("Views/Img/Logo.png").toString()));
        }catch (Exception e) {

        }
        stage.setTitle("Welcome !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}