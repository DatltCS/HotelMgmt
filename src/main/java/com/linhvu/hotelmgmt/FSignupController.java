/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Account;
import com.linhvu.pojo.Customer;
import com.linhvu.services.AccountServices;
import com.linhvu.services.CustomerServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

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
    @FXML Button btnSignup;

    AccountServices aS = new AccountServices();
    CustomerServices cS = new CustomerServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Init date picker -- không cho phép chọn ngày tháng năm sinh là hiện tại
        dpBirthday.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(b || localDate.compareTo(LocalDate.now()) >= 0);
            }
        });

        // set format của ngày tháng năm về định dạng dd/MMM/yyyy
        dpBirthday.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    public void btnSignupClick(ActionEvent event) throws SQLException, ParseException {
        if (txtfID.getText().isEmpty() || txtfFName.getText().isEmpty() || txtfLName.getText().isEmpty() ||
                dpBirthday.getEditor().getText().isEmpty() || txtfPhone.getText().isEmpty() || pfPass.getText().isEmpty())
            Utils.getBox("Please fill out all field.", Alert.AlertType.WARNING).show();
        else if (!aS.validateUserID(txtfID.getText().trim()))
            Utils.getBox("Your ID is invalid or used by another account.", Alert.AlertType.WARNING).show();
        else if (cS.vadidatePhoneNum(txtfPhone.getText())){
            // Khởi tạo thông tin tài khoản và khách hàng
            Account a = new Account(txtfID.getText().trim(), pfPass.getText(), Account.AccountType.customer);
            Customer c = new Customer(a.getUserID(), txtfFName.getText(), txtfLName.getText(), dpBirthday.getValue(), txtfPhone.getText());

            // Kiểm tra tính bảo mật của password
            int passCheck = aS.validatePassword(a.getUserPass());
            if (passCheck != 1) {
                switch(passCheck) {
                    case 0:
                        Utils.getBox("Password can not contain a blank space.", Alert.AlertType.WARNING).show();
                        break;
                    case -1:
                        Utils.getBox("Your password must be 8 - 16 characters!", Alert.AlertType.ERROR).show();
                        break;
                    case -2:
                        Utils.getBox("Password must contains lower, upper, special character and a number.", Alert.AlertType.WARNING).show();
                        break;
                    default:
                        Utils.getBox("Your password is not string enough!", Alert.AlertType.WARNING).show();
                        break;
                }
            } else if (aS.addAccountData(a) && cS.addCustomerData(c)) {
                Utils.getBox("Sign up success!", Alert.AlertType.INFORMATION).show();

                txtfID.setText("");
                txtfFName.setText("");
                txtfLName.setText("");
                txtfPhone.setText("");
                pfPass.setText("");
                dpBirthday.setValue(null);
            }
            else
                Utils.getBox("Fail to sign up!", Alert.AlertType.ERROR).show();
        } else {
            Utils.getBox("Phone number invalid!", Alert.AlertType.WARNING).show();
        }
    }
}
