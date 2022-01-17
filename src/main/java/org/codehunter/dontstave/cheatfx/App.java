package org.codehunter.dontstave.cheatfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehunter.dontstave.cheatfx.model.Item;
import org.codehunter.dontstave.cheatfx.service.InventoryMenuItemFactory;
import org.codehunter.dontstave.cheatfx.util.CsvUtil;

import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final Logger log = LogManager.getLogger(App.class);

    @Override
    public void start(Stage stage) {
        log.info("App start!");
        stage.setTitle("Don't Stave Cheat Tool");
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        Menu menu = new Menu("Generate");
        Menu spawnMenu = new Menu("Spawn");
        Menu giveMenu = new Menu("Give");
        Menu giveInventoryMenu = new Menu("Inventory");

        String path = "inventory_data.csv";
        List<Item> listItemFromCsv = CsvUtil.getListItemFromCsv(path, true);
        InventoryMenuItemFactory inventoryMenuItemFactory = new InventoryMenuItemFactory();

        listItemFromCsv.stream()
                .peek(item -> log.info("create menu item: " + item.name()))
                .map(inventoryMenuItemFactory::createMenuItem)
                .forEach(giveInventoryMenu.getItems()::add);

        giveMenu.getItems().add(giveInventoryMenu);

        MenuItem turnOffLog = new MenuItem("Turn off log");
        turnOffLog.setOnAction(actionEvent -> {
            Robot robot = new Robot();
            robot.mouseMove(400, 400);
            robot.mousePress(MouseButton.PRIMARY);
            robot.mouseRelease(MouseButton.PRIMARY);
            robot.keyPress(KeyCode.CONTROL);
            robot.keyPress(KeyCode.L);
            robot.keyRelease(KeyCode.L);
            robot.keyRelease(KeyCode.CONTROL);
        });
        menu.getItems().addAll(giveMenu, spawnMenu, turnOffLog);

        MenuBar menuBar = new MenuBar(menu);
        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        VBox vBox = new VBox(menuBar);
        vBox.getChildren().add(label);

//        TextField textField = new TextField();
//        textField.setOnKeyPressed(keyEvent -> {
//            String type = keyEvent.getEventType().getName();
//            KeyCode keyCode = keyEvent.getCode();
//            System.out.println("type: " + type + ", Code: " + keyCode);
//        });
//        vBox.getChildren().add(textField);

        var scene = new Scene(vBox, 300, 80);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}