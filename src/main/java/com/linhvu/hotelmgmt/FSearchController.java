/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.services.BookingService;
import com.linhvu.services.CustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FSearchController implements Initializable {
    @FXML DatePicker dtCheckin;
    @FXML DatePicker dtCheckout;
    @FXML MenuButton menuBtn;
    @FXML GridPane gpResult;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ((long) menuBtn.getItems().size() < 1)
            loadMenuButton(true);

        // init DatePicker
        Utils.initDatePicker(dtCheckin);
        Utils.initDatePicker(dtCheckout);

        // Định dạng lại điều kiện của DatePicker mỗi khi có ngày được chọn
        dtCheckin.valueProperty().addListener((observableValue, localDate, t1) -> reinitDtCheckout());
        dtCheckout.valueProperty().addListener((observableValue, localDate, t1) -> reinitDtCheckin());
    }

    public void loadMenuButton(boolean key) {
        // key -> đánh dấu đã xác thực đăng nhập hay chưa
        if (key) {
            this.menuBtn.setText("Welcome, " + CustomerService.customer.getfName());
            Utils.loadCustomerMenuItem(menuBtn);
        } else
            this.menuBtn.setText("TESTING");
    }

    public void reinitDtCheckout() {
        dtCheckout.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(b || localDate.compareTo(LocalDate.now()) < 0 || localDate.compareTo(dtCheckin.getValue()) <= 0);
            }
        });
    }

    public void reinitDtCheckin() {
        dtCheckin.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(b || localDate.compareTo(LocalDate.now()) < 0 || localDate.compareTo(dtCheckout.getValue()) >= 0);
            }
        });
    }
}
