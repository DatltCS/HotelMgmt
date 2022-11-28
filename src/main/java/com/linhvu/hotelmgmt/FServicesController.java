/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.util.ResourceBundle;

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
        // TODO
    }    
    
}
