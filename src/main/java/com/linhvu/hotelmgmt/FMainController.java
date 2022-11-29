/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Customer;
import com.linhvu.services.CustomerService;
import javafx.event.ActionEvent;
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
        if ((long) menuBtn.getItems().size() < 1)
            loadMenuButton(false);

        // init DatePicker
        Utils.initDatePicker(dateCheckin);
        Utils.initDatePicker(dateCheckout);

        // Định dạng lại điều kiện của DatePicker mỗi khi có ngày được chọn
        dateCheckin.valueProperty().addListener((observableValue, localDate, t1) -> reinitDtCheckout());
        dateCheckout.valueProperty().addListener((observableValue, localDate, t1) -> reinitDtCheckin());
    }

    public void btnSearchClick(ActionEvent event) {
        // TODO: lấy + truyền dữ liệu ngày sang form Search, mở FSearch
        // Cần truyền: startDate, endDate, userID
    }

    public void getCustomerData(String UserID) throws SQLException {
        CustomerService cs = new CustomerService();
        this.c = cs.getCustomerData(UserID);
    }

    public void getCustomer(Customer cus) {
        this.c = cus;
    }

    public void loadMenuButton(boolean key) {
        // key -> đánh dấu đã xác thực đăng nhập hay chưa
        if (!key) {
            this.menuBtn.setText("TESTING");
            Utils.loadCustomerMenuItem(menuBtn, c);
        } else
            this.menuBtn.setText("Welcome, " + c.getfName());
    }

    public void reinitDtCheckout() {
        dateCheckout.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(b || localDate.compareTo(LocalDate.now()) < 0 || localDate.compareTo(dateCheckin.getValue()) <= 0);
            }
        });
    }

    public void reinitDtCheckin() {
        dateCheckin.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(b || localDate.compareTo(LocalDate.now()) < 0 || localDate.compareTo(dateCheckout.getValue()) >= 0);
            }
        });
    }
}
