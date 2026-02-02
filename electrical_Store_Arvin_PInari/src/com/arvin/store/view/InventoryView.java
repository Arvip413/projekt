package com.arvin.store.view;

import com.arvin.store.controller.InventoryController;
import com.arvin.store.model.Item;
import com.arvin.store.model.User;
import com.arvin.store.util.InvalidInputException;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventoryView {

    private Stage stage;
    private User user;
    private InventoryController controller;
    private TableView<Item> table;

    public InventoryView(Stage stage, User user) {
        this.stage = stage;
        this.user = user;
        this.controller = new InventoryController();
    }

    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label label = new Label("Inventory Management");
        label.setStyle("-fx-font-size: 20px;");

        // Table
        table = new TableView<>();
        setupTable();
        refreshTable();

        // Form
        VBox form = createForm();

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> new DashboardView(stage, user).show());

        layout.getChildren().addAll(label, table, form, backBtn);

        Scene scene = new Scene(layout, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @SuppressWarnings("unchecked")
    private void setupTable() {
        TableColumn<Item, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Item, String> catCol = new TableColumn<>("Category");
        catCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Item, Double> pPriceCol = new TableColumn<>("Buy Price");
        pPriceCol.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));

        TableColumn<Item, Double> sPriceCol = new TableColumn<>("Sell Price");
        sPriceCol.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

        TableColumn<Item, Integer> qtyCol = new TableColumn<>("Qty");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table.getColumns().addAll(nameCol, catCol, pPriceCol, sPriceCol, qtyCol);
    }

    private void refreshTable() {
        table.setItems(FXCollections.observableArrayList(controller.getAllItems()));
    }

    private VBox createForm() {
        VBox box = new VBox(5);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField catField = new TextField();
        catField.setPromptText("Category");
        TextField supplField = new TextField();
        supplField.setPromptText("Supplier");
        TextField dateField = new TextField();
        dateField.setPromptText("Date"); // Simple text for date
        TextField pPriceField = new TextField();
        pPriceField.setPromptText("Purchase Price");
        TextField sPriceField = new TextField();
        sPriceField.setPromptText("Selling Price");
        TextField qtyField = new TextField();
        qtyField.setPromptText("Quantity");

        Button addBtn = new Button("Add Item");
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        addBtn.setOnAction(e -> {
            try {
                controller.addItem(
                        nameField.getText(),
                        catField.getText(),
                        supplField.getText(),
                        dateField.getText(),
                        pPriceField.getText(),
                        sPriceField.getText(),
                        qtyField.getText());
                refreshTable();
                errorLabel.setText("Item added successfully!");
                errorLabel.setStyle("-fx-text-fill: green;");
            } catch (InvalidInputException ex) {
                errorLabel.setText(ex.getMessage());
                errorLabel.setStyle("-fx-text-fill: red;");
            }
        });

        // Layout for inputs
        HBox row1 = new HBox(5, nameField, catField, supplField);
        HBox row2 = new HBox(5, dateField, pPriceField, sPriceField, qtyField);

        box.getChildren().addAll(new Label("Add New Item:"), row1, row2, addBtn, errorLabel);
        return box;
    }
}
