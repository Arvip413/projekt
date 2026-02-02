package com.arvin.store.view;

import com.arvin.store.controller.AuthenticationService;
import com.arvin.store.model.User;
import com.arvin.store.util.AuthException;
import com.arvin.store.util.InvalidInputException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginView {
    private Stage stage;
    private AuthenticationService authService;

    public LoginView(Stage stage) {
        this.stage = stage;
        this.authService = new AuthenticationService();
    }

    public void show() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Logo
        try {
            Image logoImage = new Image(getClass().getResource("/logo.png").toExternalForm());
            ImageView imageView = new ImageView(logoImage);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);
            grid.add(imageView, 0, 0, 2, 1);
            GridPane.setHalignment(imageView, javafx.geometry.HPos.CENTER);
        } catch (Exception e) {
            System.out.println("Logo not found: " + e.getMessage());
        }

        Label title = new Label("Melpi Login");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(title, 0, 1, 2, 1);
        GridPane.setHalignment(title, javafx.geometry.HPos.CENTER);

        Label userName = new Label("Username:");
        grid.add(userName, 0, 2);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 2);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 3);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 3);

        Label actiontarget = new Label();
        grid.add(actiontarget, 1, 5);

        Button btn = new Button("Sign in");
        grid.add(btn, 1, 4);

        btn.setOnAction(e -> {
            try {
                User user = authService.login(userTextField.getText(), pwBox.getText());
                // Navigate to Dashboard
                new DashboardView(stage, user).show();
            } catch (AuthException | InvalidInputException ex) {
                actiontarget.setText(ex.getMessage());
                actiontarget.setStyle("-fx-text-fill: firebrick;");
            }
        });

        Scene scene = new Scene(grid, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
