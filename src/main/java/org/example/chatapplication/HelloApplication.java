package org.example.chatapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        startServer();
        startClient();
    }
    void startServer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/server.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 618.0, 442);
        stage.setTitle("Server!");
        stage.setScene(scene);
        stage.show();
    }
    void startClient() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/client.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 618.0, 442);
        stage.setTitle("Client 01!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}