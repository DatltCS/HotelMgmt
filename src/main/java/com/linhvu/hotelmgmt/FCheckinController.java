/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.services.EmployeeService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FCheckinController implements Initializable {
    @FXML TextField txtfInputID;
    @FXML Button btnSearch;
    @FXML MenuButton menuBtn;
    @FXML TableView tbvData;
    @FXML Button btnCheckin;
    @FXML Button btnCheckout;
    @FXML Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ((long) menuBtn.getItems().size() < 1)
            loadMenuButton(true);
    }

    public void loadMenuButton(boolean key) {
        // key -> đánh dấu đã xác thực đăng nhập hay chưa
        if (key) {
            this.menuBtn.setText("Welcome, " + EmployeeService.employee.getfName());
            Utils.loadEmployeeMenuItem(menuBtn, EmployeeService.employee);
        }
        else
            this.menuBtn.setText("TESTING");
    }
}
