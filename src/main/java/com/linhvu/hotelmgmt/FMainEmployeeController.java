/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Employee;
import com.linhvu.services.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FMainEmployeeController implements Initializable {
    @FXML MenuButton menuBtn;
    @FXML Button btnCheckInOut;
    @FXML Button btnBookDetails;
    @FXML Button btnManageRoom;

    private Employee e = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ((long) menuBtn.getItems().size() < 1)
            loadMenuButton(false);
    }

    public void btnCheckInOutClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FCheckin.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FCheckinController checkinCtrl = fxmlLoader.getController();
        checkinCtrl.getEmployee(this.e);
        checkinCtrl.loadMenuButton(false);

        Scene scene = new Scene(root);
        Stage stage = (Stage)menuBtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void btnBookDetailsClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FServicesEmployee.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FServicesEmployeeController secCtrl = fxmlLoader.getController();
        secCtrl.getEmployee(this.e);
        secCtrl.loadMenuButton(false);

        Scene scene = new Scene(root);
        Stage stage = (Stage)menuBtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void btnManageRoomClick(ActionEvent event) {
        // Chưa xử lý
    }

    public void getEmployeeData(String userID) throws SQLException {
        EmployeeService es = new EmployeeService();
        e = es.getEmployeeData(userID);
    }

    public void getEmployee(Employee emp) {
        this.e = emp;
    }

    public void loadMenuButton(boolean key) {
        // key -> đánh dấu đã xác thực đăng nhập hay chưa
        if (!key) {
            this.menuBtn.setText("TESTING");
            Utils.loadEmployeeMenuItem(menuBtn, this.e);
        }
        else
            this.menuBtn.setText("Welcome, " + e.getfName());
    }
}
