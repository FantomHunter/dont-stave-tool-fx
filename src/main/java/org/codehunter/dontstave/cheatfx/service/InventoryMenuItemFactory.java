package org.codehunter.dontstave.cheatfx.service;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.robot.Robot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehunter.dontstave.cheatfx.App;
import org.codehunter.dontstave.cheatfx.model.Item;

import java.io.InputStream;

public class InventoryMenuItemFactory implements IMenuItemFactory {
    Logger log = LogManager.getLogger(InventoryMenuItemFactory.class);

    @Override
    public MenuItem createMenuItem(Item item) {
        MenuItem menuItem = new MenuItem(item.name());
        log.info("item imageurl: " + item.imageUrl());
        ImageView imageView = new ImageView();
        InputStream imageUrl = App.class.getClassLoader().getResourceAsStream("/"+ item.imageUrl());
        if (imageUrl != null) {
            Image image = new Image(imageUrl);
            imageView.setImage(image);
        } else {
            InputStream defaultImage = App.class.getClassLoader().getResourceAsStream("/image/default.png");
            if (defaultImage != null) {
                imageView.setImage(new Image(defaultImage));
            }
        }
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        menuItem.setGraphic(imageView);
        menuItem.setOnAction(e -> {
            try {
                addBoard(item.code());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        return menuItem;
    }

    private void addBoard(String code) throws InterruptedException {
        Robot robot = new Robot();
        robot.mouseMove(400, 400);
        robot.mousePress(MouseButton.PRIMARY);
        robot.mouseRelease(MouseButton.PRIMARY);
        robot.keyType(KeyCode.BACK_QUOTE);
        clip(code);
        Thread.sleep(100);
        robot.keyPress(KeyCode.CONTROL);
        robot.keyPress(KeyCode.V);
        robot.keyRelease(KeyCode.CONTROL);
        robot.keyRelease(KeyCode.V);
        robot.keyType(KeyCode.ENTER);

//        robot.keyPress(KeyCode.CONTROL);
//        robot.keyPress(KeyCode.L);
//        robot.keyRelease(KeyCode.L);
//        robot.keyRelease(KeyCode.CONTROL);
    }

    public static void clip(String code) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString("c_give(\"" + code + "\", 10)");
        clipboard.setContent(content);
    }
}
