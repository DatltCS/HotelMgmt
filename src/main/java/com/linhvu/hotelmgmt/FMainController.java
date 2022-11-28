/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Customer;
import com.linhvu.services.CustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
        Utils.loadCustomerMenuItem(menuBtn);
    }
}
