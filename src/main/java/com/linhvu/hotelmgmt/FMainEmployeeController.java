/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.services.EmployeeServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FMainEmployeeController implements Initializable {
    @FXML MenuButton menuBtn;
    @FXML Button btnCheckInOut;
    @FXML Button btnBookDetails;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ((long) menuBtn.getItems().size() < 1)
            loadMenuButton(true);
    }

    public void btnCheckInOutClick(ActionEvent event) {
        try {
            App.setRoot("FCheckin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnBookDetailsClick(ActionEvent event) {
        try {
            App.setRoot("FServicesEmployee");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMenuButton(boolean key) {
        // key -> đánh dấu đã xác thực đăng nhập hay chưa
        if (key) {
            this.menuBtn.setText("Welcome, " + EmployeeServices.employee.getfName());
            Utils.loadEmployeeMenuItem(menuBtn, EmployeeServices.employee);
        }
        else
            this.menuBtn.setText("TESTING");
    }
}
