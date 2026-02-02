package com.arvin.store.view;

import com.arvin.store.controller.InventoryController;
import com.arvin.store.controller.SalesController;
import com.arvin.store.model.Item;
import com.arvin.store.model.SoldItem;
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

import java.util.Optional;

public class SalesView {

    private Stage stage;
    private User user;
    private SalesController salesController;
    private InventoryController inventoryController;
    private TableView<SoldItem> cartTable;
    private Label totalLabel;

    public SalesView(Stage stage, User user) {
        this.stage = stage;
        this.user = user;
        this.salesController = new SalesController(user);
        this.inventoryController = new InventoryController();
    }

    public void show() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label label = new Label("Sales Counter - " + salesController.getCurrentSale().getBillNumber());
        label.setStyle("-fx-font-size: 20px;");

        // Cart Table
        cartTable = new TableView<>();
        TableColumn<SoldItem, String> nameCol = new TableColumn<>("Item");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<SoldItem, Integer> qtyCol = new TableColumn<>("Qty");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<SoldItem, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        cartTable.getColumns().addAll(nameCol, qtyCol, priceCol);

        // Item Selection
        HBox selectionBox = new HBox(10);
        ComboBox<String> itemSelector = new ComboBox<>();
        // Populate with item names
        for (Item i : inventoryController.getAllItems()) {
            itemSelector.getItems().add(i.getName());
        }

        TextField qtyField = new TextField("1");
        qtyField.setPrefWidth(50);
        Button addBtn = new Button("Add to Cart");
        Label msgLabel = new Label();

        addBtn.setOnAction(e -> {
            String selectedName = itemSelector.getValue();
            if (selectedName == null) {
                msgLabel.setText("Select an item.");
                return;
            }

            // Find the full item object
            Optional<Item> itemOpt = inventoryController.getAllItems().stream()
                    .filter(i -> i.getName().equals(selectedName))
                    .findFirst();

            if (itemOpt.isPresent()) {
                try {
                    int qty = Integer.parseInt(qtyField.getText());
                    salesController.addToCart(itemOpt.get(), qty);
                    refreshCart();
                    msgLabel.setText("");
                } catch (NumberFormatException nfe) {
                    msgLabel.setText("Invalid qty.");
                } catch (InvalidInputException ex) {
                    msgLabel.setText(ex.getMessage());
                }
            }
        });

        selectionBox.getChildren().addAll(new Label("Item:"), itemSelector, new Label("Qty:"), qtyField, addBtn);

        // Checkout
        totalLabel = new Label("Total: $0.00");
        totalLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Button checkoutBtn = new Button("Checkout / Print Bill");
        checkoutBtn.setOnAction(e -> {
            salesController.checkout();
            cartTable.getItems().clear();
            totalLabel.setText("Total: $0.00");
            label.setText("Sales Counter - " + salesController.getCurrentSale().getBillNumber());
            msgLabel.setText("Sale Completed!");
            // Refresh inventory list in selector
            itemSelector.getItems().clear();
            for (Item i : inventoryController.getAllItems()) {
                itemSelector.getItems().add(i.getName());
            }
        });

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> new DashboardView(stage, user).show());

        layout.getChildren().addAll(label, cartTable, totalLabel, selectionBox, msgLabel, checkoutBtn, backBtn);

        Scene scene = new Scene(layout, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void refreshCart() {
        cartTable.setItems(FXCollections.observableArrayList(salesController.getCurrentSale().getItems()));
        totalLabel.setText(String.format("Total: $%.2f", salesController.getTotal()));
    }
}
