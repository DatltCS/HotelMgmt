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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FBookingController implements Initializable {
    @FXML Text txtRoom;
    @FXML Text txtType;
    @FXML Text txtCheckin;
    @FXML Text txtCheckout;
    @FXML Text txtPrice;
    @FXML ComboBox cbServices;
    @FXML Text txtTotal;
    @FXML Button btnFinish;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
