/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FMainGuestController implements Initializable {
    @FXML Button btnSignup;
    @FXML Button btnSignin;
    @FXML DatePicker dtCheckin;
    @FXML DatePicker dtCheckout;
    @FXML Button btnSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // init DatePicker
        initDatePicker(dtCheckin);
        initDatePicker(dtCheckout);

        // Định dạng lại điều kiện của DatePicker mỗi khi có ngày được chọn
        dtCheckin.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                reinitDtCheckout();
            }
        });

        dtCheckout.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                reinitDtCheckin();
            }
        });
    }

    // Chỉ cho phép chọn ngày bắt đầu từ hiện tại (không được chọn ngày trong quá khứ)
    public void initDatePicker(DatePicker dtParam) {
        dtParam.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(b || localDate.compareTo(LocalDate.now()) < 0);
            }
        });
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

    public void btnSignIn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Hotel del Luna Management App");
        stage.show();

        Utils.centerScreen(stage);
    }

    public void btnSignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FSignup.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sign up here!");

        // điều chỉnh không cho phép tương tác khi đang mở sign-up
        stage.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void btnSearchDate(ActionEvent event) {
        Utils.getBox("Please Sign in first!", Alert.AlertType.WARNING).showAndWait();
    }
}
