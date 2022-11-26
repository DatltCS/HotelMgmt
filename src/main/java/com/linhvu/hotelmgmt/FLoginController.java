/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import com.linhvu.pojo.Account;
import com.linhvu.pojo.Account.AccountType;
import com.linhvu.services.AccountService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FLoginController implements Initializable {
    @FXML ComboBox<AccountType> cbUserType;
    @FXML TextField txtID;
    @FXML PasswordField txtPassword;
    @FXML Text errorText;
    @FXML Button btnSignup;
    @FXML Button btnSignin;
    
    public static String account;
    public static boolean logged = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbUserType.setItems(FXCollections.observableArrayList(AccountType.values()));
    }    
    
    public void btnSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FSignup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sign up here!");
        stage.show();
    }
    
    public void btnSignIn(ActionEvent event) throws SQLException {
        if (this.txtID.getText().isBlank() || this.txtPassword.getText().isBlank()) {
            this.errorText.setText("Please fill out all field!");
        } else if (this.cbUserType.getValue() == null) {
            this.errorText.setText("Please choose your account type!");
        } else {
            Account a = new Account();
            AccountService as = new AccountService();

            a.setUserID(this.txtID.getText());
            a.setUserPass(this.txtPassword.getText());
            a.setType(this.cbUserType.getValue());

            if (as.validateLogin(a)) {
                // xử lý chuyển sang giao diện người dùng chính
                switch (a.getType()) {
                    case customer:
                        this.errorText.setText("Customer!");
                        break;
                    case employee:
                        this.errorText.setText("Employee!");
                        break;
                }
            }
            else
                this.errorText.setText("UserID or Password incorrect!");
        }
    }
}
























