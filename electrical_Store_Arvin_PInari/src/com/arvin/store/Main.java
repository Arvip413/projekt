package com.arvin.store;

import com.arvin.store.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Melpi - Electrical Store");

        // Start with Login View
        new LoginView(primaryStage).show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
