/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.conf;

import com.linhvu.hotelmgmt.*;
import com.linhvu.pojo.Customer;
import com.linhvu.pojo.Employee;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author prodi
 */
public class Utils {
    public static Alert getBox(String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(content);

        return alert;
    }

    // Xử lý đưa ứng dụng về giữa màn hình người dùng
    public static void centerScreen(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void loadCustomerMenuItem(MenuButton menuBtn, Customer customer) {
        // Xử lý menu người dùng
        MenuItem itemBook = new MenuItem("Book a room");
        MenuItem itemServices = new MenuItem("Room services");
        MenuItem itemSignOut = new MenuItem("Sign out");

        menuBtn.getItems().add(itemBook);
        menuBtn.getItems().add(itemServices);
        menuBtn.getItems().add(itemSignOut);

        // Xử lý các Menu button item => sự kiện click
        itemBook.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FMain.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FMainController mainCtrl = fxmlLoader.getController();
            mainCtrl.getCustomer(customer);
            mainCtrl.loadMenuButton(false);

            Scene scene = new Scene(root);
            Stage stage = (Stage)menuBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });

        itemServices.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FServices.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FServicesController sc = fxmlLoader.getController();
            sc.getCustomer(customer);
            sc.loadMenuButton(false);

            Scene scene = new Scene(root);
            Stage stage = (Stage)menuBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });

        itemSignOut.setOnAction(event -> {
            Scene scene = null;
            try {
                scene = new Scene(new FXMLLoader(App.class.getResource("FLogin.fxml")).load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage)menuBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Hotel del Luna - Sign in");
            stage.show();
            Utils.centerScreen(stage);
        });
    }

    public static void loadEmployeeMenuItem(MenuButton menuBtn, Employee employee) {
        // Xử lý menu người dùng
        MenuItem itemMain = new MenuItem("Employee main page");
        MenuItem itemGuest = new MenuItem("Check in/out guest");
        MenuItem itemBooking = new MenuItem("Check/Edit booking details");
//        MenuItem itemRoom = new MenuItem("Manage rooms");
        MenuItem itemSignOut = new MenuItem("Sign out");

        menuBtn.getItems().add(itemMain);
        menuBtn.getItems().add(itemGuest);
        menuBtn.getItems().add(itemBooking);
//        menuBtn.getItems().add(itemRoom);
        menuBtn.getItems().add(itemSignOut);

        itemMain.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FMainEmployee.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FMainEmployeeController mainEmCtrl = fxmlLoader.getController();
            mainEmCtrl.getEmployee(employee);
            mainEmCtrl.loadMenuButton(false);

            Scene scene = new Scene(root);
            Stage stage = (Stage)menuBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });

        itemGuest.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FCheckin.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FCheckinController checkinCtrl = fxmlLoader.getController();
            checkinCtrl.getEmployee(employee);
            checkinCtrl.loadMenuButton(false);

            Scene scene = new Scene(root);
            Stage stage = (Stage)menuBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });

        itemBooking.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FServicesEmployee.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FServicesEmployeeController secCtrl = fxmlLoader.getController();
            secCtrl.getEmployee(employee);
            secCtrl.loadMenuButton(false);

            Scene scene = new Scene(root);
            Stage stage = (Stage)menuBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });

        itemSignOut.setOnAction(event -> {
            Scene scene = null;
            try {
                scene = new Scene(new FXMLLoader(App.class.getResource("FLogin.fxml")).load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage)menuBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Hotel del Luna - Sign in");
            stage.show();
            Utils.centerScreen(stage);
        });
    }

    // Chỉ cho phép chọn ngày bắt đầu từ hiện tại (không được chọn ngày trong quá khứ)
    public static void initDatePicker(DatePicker dtParam) {
        dtParam.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(b || localDate.compareTo(LocalDate.now()) < 0);
            }
        });
    }
}
