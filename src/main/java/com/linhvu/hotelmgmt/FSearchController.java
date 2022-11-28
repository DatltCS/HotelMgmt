/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FSearchController implements Initializable {
    @FXML DatePicker dtCheckin;
    @FXML DatePicker dtCheckout;
    @FXML Menu menuBtn;
    @FXML GridPane gpResult;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
