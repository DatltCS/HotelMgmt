/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FServicesEmployeeController implements Initializable {
    @FXML TextField txtfID;
    @FXML Button btnSearch;
    @FXML MenuButton menuBtn;
    @FXML TableView tbvServices;
    @FXML TableView tbvChosen;
    @FXML Button btnAdd;
    @FXML Button btnDelete;
    @FXML Button btnDeleteAll;

    private Employee e;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ((long) menuBtn.getItems().size() < 1)
            loadMenuButton(false);
    }    

    public void getEmployee(Employee emp) {
        this.e = emp;
    }

    public void loadMenuButton(boolean key) {
        // key -> đánh dấu đã xác thực đăng nhập hay chưa
        if (!key) {
            this.menuBtn.setText("TESTING");
            Utils.loadEmployeeMenuItem(menuBtn, this.e);
        }
        else
            this.menuBtn.setText("Welcome, " + e.getfName());
    }
}
