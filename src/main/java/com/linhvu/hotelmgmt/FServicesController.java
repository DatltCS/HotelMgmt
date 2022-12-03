/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.services.CustomerServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FServicesController implements Initializable {
    @FXML ComboBox cbRoomNum;
    @FXML MenuButton menuBtn;
    @FXML TableView tbvServices;
    @FXML TableView tbvChosen;
    @FXML Button btnAdd;
    @FXML Button btnDelete;
    @FXML Button btnDeleteAll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ((long) menuBtn.getItems().size() < 1)
            loadMenuButton(true);
    }    

    public void cbRoomSelect(ActionEvent event) {

    }

    public void btnAddClick(ActionEvent event) {

    }

    public void btnDeleteClick(ActionEvent event) {

    }

    public void btnDeleteAllClick(ActionEvent event) {

    }

    public void loadMenuButton(boolean key) {
        // key -> đánh dấu đã xác thực đăng nhập hay chưa
        if (key) {
            this.menuBtn.setText("Welcome, " + CustomerServices.customer.getfName());
            Utils.loadCustomerMenuItem(menuBtn);
        } else
            this.menuBtn.setText("TESTING");
    }
}
