/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.linhvu.pojo.Customer;
import com.linhvu.services.CustomerService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FMainController implements Initializable {
    @FXML MenuButton menuBtn;
    @FXML DatePicker dateCheckin;
    @FXML DatePicker dateCheckout;
    @FXML Button btnSearch;

    private Customer c = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void getCustomerData(String UserID) throws SQLException {
        CustomerService cs = new CustomerService();
        c = cs.getCustomerData(UserID);
    }

    public void loadMenuButton() {
        this.menuBtn.setText("Welcome, " + c.getfName());

        // Xử lý menu người dùng
        MenuItem itemBook = new MenuItem("Book a room");
        MenuItem itemServices = new MenuItem("Room services");
        MenuItem itemSignOut = new MenuItem("Sign out");

        menuBtn.getItems().add(itemBook);
        menuBtn.getItems().add(itemServices);
        menuBtn.getItems().add(itemSignOut);

        itemBook.setOnAction(this::itemBookClick);
        itemServices.setOnAction(this::itemServicesClick);
        itemSignOut.setOnAction(this::itemSignOutClick);
    }

    public void itemBookClick(ActionEvent event) {
        // Mở cửa sổ booking như ban đầu - FMain
    }

    public void itemServicesClick(ActionEvent event) {
        // Mở cửa sổ đặt dịch vụ - FService
    }

    public void itemSignOutClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FLogin.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hotel del Luna - Sign in");
        stage.show();

//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
//        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
