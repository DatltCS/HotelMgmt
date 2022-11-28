/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.conf;

import com.linhvu.hotelmgmt.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author prodi
 */
public class Utils {
    // Xử lý đưa ứng dụng về giữa màn hình người dùng
    public static void centerScreen(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void loadCustomerMenuItem(MenuButton menuBtn) {
        // Xử lý menu người dùng
        MenuItem itemBook = new MenuItem("Book a room");
        MenuItem itemServices = new MenuItem("Room services");
        MenuItem itemSignOut = new MenuItem("Sign out");

        menuBtn.getItems().add(itemBook);
        menuBtn.getItems().add(itemServices);
        menuBtn.getItems().add(itemSignOut);

        // Xử lý các Menu button item => sự kiện click
        itemBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        itemServices.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        itemSignOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FLogin.fxml"));
                Parent root = null;
                try {
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);
                Stage stage = (Stage)menuBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Hotel del Luna - Sign in");
                stage.show();
                Utils.centerScreen(stage);
            }
        });
    }

    public static void loadEmployeeMenuItem(MenuButton menuBtn) {
        // Xử lý menu người dùng
        MenuItem itemGuest = new MenuItem("Check in/out guest");
        MenuItem itemBooking = new MenuItem("Check/Edit booking details");
        MenuItem itemRoom = new MenuItem("Manage rooms");
        MenuItem itemSignOut = new MenuItem("Sign out");

        menuBtn.getItems().add(itemGuest);
        menuBtn.getItems().add(itemBooking);
        menuBtn.getItems().add(itemRoom);
        menuBtn.getItems().add(itemSignOut);

        itemGuest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        itemBooking.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        itemRoom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        itemSignOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FLogin.fxml"));
                Parent root = null;
                try {
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);
                Stage stage = (Stage)menuBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Hotel del Luna - Sign in");
                stage.show();
                Utils.centerScreen(stage);
            }
        });
    }
}
