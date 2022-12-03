/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.linhvu.pojo.Booking;
import com.linhvu.services.ServiceServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import org.controlsfx.control.CheckComboBox;

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
    @FXML CheckComboBox<String> cbServices;
    @FXML Text txtTotal;
    @FXML Button btnFinish;

    private Booking book;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceServices sS = new ServiceServices();
        try {
            ObservableList<String> servicesName = FXCollections.observableArrayList(sS.getServiceNameList());
            cbServices.getItems().addAll(servicesName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setData(Booking b) {
        this.book = b;
    }
    
}
