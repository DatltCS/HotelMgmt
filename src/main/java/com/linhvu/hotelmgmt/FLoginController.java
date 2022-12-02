/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Account;
import com.linhvu.pojo.Account.AccountType;
import com.linhvu.services.AccountService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.linhvu.services.CustomerService;
import com.linhvu.services.EmployeeService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbUserType.setItems(FXCollections.observableArrayList(AccountType.values()));
    }    
    
    public void btnSignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FSignup.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sign up here!");

        // điều chỉnh không cho phép tương tác với login khi đang mở sign-up
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    public void btnSignIn(ActionEvent event) throws SQLException, IOException {
        if (this.txtID.getText().isBlank() || this.txtPassword.getText().isBlank()) {
            this.errorText.setText("Please fill out all field!");
        } else if (this.cbUserType.getValue() == null) {
            this.errorText.setText("Please choose your account type!");
        } else {
            this.errorText.setText(" ");

            Account a = new Account();
            AccountService as = new AccountService();
            a.setUserID(this.txtID.getText());
            a.setUserPass(this.txtPassword.getText());
            a.setType(this.cbUserType.getValue());

            if (as.validateLogin(a)) {
                // xử lý chuyển sang giao diện người dùng chính
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                switch (a.getType()) {
                    case customer:
                        // Xử lý xuất hiện màn hình khách hàng
                        CustomerService cS = new CustomerService();
                        CustomerService.customer = cS.getCustomerData(a.getUserID());
                        App.setRoot("FMain");
                        stage.sizeToScene();
                        Utils.centerScreen(stage);
                        break;

                    case employee:
//                        // Xử lý xuất hiện màn hình nhân viên
                        EmployeeService eS = new EmployeeService();
                        EmployeeService.employee = eS.getEmployeeData(a.getUserID());
                        App.setRoot("FMainEmployee");
                        stage.sizeToScene();
                        Utils.centerScreen(stage);
                        break;
                }
            }
            else
                this.errorText.setText("UserID or Password incorrect!");
        }
    }
}
























