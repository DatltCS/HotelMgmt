/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Employee;
import com.linhvu.services.EmployeeService;
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
    @FXML Button btnManageRoom;

    private Employee e = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void getEmployeeData(String userID) throws SQLException {
        EmployeeService es = new EmployeeService();
        e = es.getEmployeeData(userID);
    }

    public void loadMenuButton() {
        this.menuBtn.setText("Welcome, " + e.getfName());
        Utils.loadEmployeeMenuItem(menuBtn);
    }
}
