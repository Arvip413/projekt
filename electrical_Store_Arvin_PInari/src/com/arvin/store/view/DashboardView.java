package com.arvin.store.view;

import com.arvin.store.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DashboardView {
    private Stage stage;
    private User user;

    public DashboardView(Stage stage, User user) {
        this.stage = stage;
        this.user = user;
    }

    public void show() {
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20));

        // Header
        Label header = new Label("Melpi - Welcome, " + user.getUsername() + " (" + user.getRoleName() + ")");
        header.setFont(new Font("Arial", 24));
        border.setTop(header);
        BorderPane.setAlignment(header, Pos.CENTER);

        // Menu
        VBox menu = new VBox(15);
        menu.setAlignment(Pos.CENTER);

        if (user.canManageInventory()) {
            Button invBtn = new Button("Manage Inventory");
            invBtn.setMinWidth(150);
            invBtn.setOnAction(e -> new InventoryView(stage, user).show());
            menu.getChildren().add(invBtn);
        }

        if (user.canProcessSales()) {
            Button salesBtn = new Button("Sales Counter");
            salesBtn.setMinWidth(150);
            salesBtn.setOnAction(e -> new SalesView(stage, user).show());
            menu.getChildren().add(salesBtn);
        }

        if (user.canManageUsers()) {
            Button usersBtn = new Button("User Management"); // Placeholder
            usersBtn.setMinWidth(150);
            usersBtn.setDisable(true); // Feature not fully requested/implemented in this scope
            menu.getChildren().add(usersBtn);
        }

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> new LoginView(stage).show());
        menu.getChildren().add(logoutBtn);

        border.setCenter(menu);

        Scene scene = new Scene(border, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
