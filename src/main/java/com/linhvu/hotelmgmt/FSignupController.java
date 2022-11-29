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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FSignupController implements Initializable {
    @FXML TextField txtfID;
    @FXML TextField txtfLName;
    @FXML TextField txtfFName;
    @FXML DatePicker dpBirthday;
    @FXML TextField txtfPhone;
    @FXML PasswordField pfPass;
    @FXML PasswordField pfConfirmPass;
    @FXML Button btnSignup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
