package com.pulsepoint.games.snake.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * UI entry point for the app.
 * 
 * @author avenkatraman
 */
public class SnakeUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(false);
        primaryStage.setTitle("Snake Game");
        Parent root = FXMLLoader.load(getClass().getResource("/snake.fxml"));
        final Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(SnakeUI.class.getResource("/snake.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
