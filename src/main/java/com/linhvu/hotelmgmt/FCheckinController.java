/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Booking;
import com.linhvu.pojo.Customer;
import com.linhvu.services.BookingServices;
import com.linhvu.services.CustomerServices;
import com.linhvu.services.EmployeeServices;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FCheckinController implements Initializable {
    @FXML TextField txtfInputID;
    @FXML Button btnSearch;
    @FXML MenuButton menuBtn;
    @FXML TableView<Booking> tbvData;
    @FXML Button btnCheckin;
    @FXML Button btnCheckout;
    @FXML Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ((long) menuBtn.getItems().size() < 1)
            loadMenuButton(true);

        loadTableView();
    }

    public void btnSearchClick(ActionEvent event) {
        if (validateCustomerID()) {
            loadTbvData();
        } else
            this.tbvData.getItems().clear();
    }

    public void btnCheckInClick(ActionEvent event) {
        Booking b = this.tbvData.getSelectionModel().getSelectedItem();
        if (b != null) {
            BookingServices bS = new BookingServices();
            if (!bS.updateBookingStatus(b, Booking.BookingStatus.checkedin))
                Utils.getBox("Can't check this booking in!", Alert.AlertType.INFORMATION).show();
            else {
                bS.updateBookingDate(b, 1);
                Utils.getBox("Check in success!", Alert.AlertType.INFORMATION).show();
            }
            loadTbvData();
        }
    }

    public void btnCheckOutClick(ActionEvent event) {
        Booking b = this.tbvData.getSelectionModel().getSelectedItem();
        if (b != null) {
            BookingServices bS = new BookingServices();
            if (!bS.updateBookingStatus(b, Booking.BookingStatus.checkedout))
                Utils.getBox("Can't check this booking out!", Alert.AlertType.INFORMATION).show();
            else {
                bS.updateBookingDate(b, 2);
                Utils.getBox("Check out success!", Alert.AlertType.INFORMATION).show();
            }
            loadTbvData();
        }
    }

    public void btnCancelClick(ActionEvent event) {
        Booking b = this.tbvData.getSelectionModel().getSelectedItem();
        if (b != null) {
            BookingServices bS = new BookingServices();
            if (!bS.updateBookingStatus(b, Booking.BookingStatus.canceled))
                Utils.getBox("Can't cancel this booking!", Alert.AlertType.INFORMATION).show();
            else
                Utils.getBox("Cancel success!", Alert.AlertType.INFORMATION).show();

            loadTbvData();
        }
    }

    public boolean validateCustomerID() {
        if (txtfInputID.getText().trim().length() == 0) {
            Utils.getBox("Insert User ID first!", Alert.AlertType.WARNING).show();
            return false;
        }
        else if (!Utils.isInterger(txtfInputID.getText())) {
            Utils.getBox("Not a valid User ID!", Alert.AlertType.ERROR).show();
            return false;
        }
        else {
            CustomerServices cS = new CustomerServices();
            try {
                Customer c = cS.getCustomerData(txtfInputID.getText());
                if (c == null) {
                    Utils.getBox("This User ID does not exist!", Alert.AlertType.ERROR).show();
                    return false;
                } else
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void loadMenuButton(boolean key) {
        // key -> đánh dấu đã xác thực đăng nhập hay chưa
        if (key) {
            this.menuBtn.setText("Welcome, " + EmployeeServices.employee.getfName());
            Utils.loadEmployeeMenuItem(menuBtn, EmployeeServices.employee);
        }
        else
            this.menuBtn.setText("TESTING");
    }

    public void loadTableView() {
        TableColumn<Booking, Integer> colBID = new TableColumn<>("Booking ID");
        colBID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        colBID.setPrefWidth(100);

        TableColumn<Booking, Integer> colCID = new TableColumn<>("CustomerID");
        colCID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colCID.setPrefWidth(100);

        TableColumn<Booking, LocalDate> colIn = new TableColumn<>("Start Date");
        colIn.setCellValueFactory(new PropertyValueFactory<>("stateDate"));
        colIn.setPrefWidth(170);

        TableColumn<Booking, LocalDate> colOut = new TableColumn<>("End Date");
        colOut.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colOut.setPrefWidth(170);

        TableColumn<Booking, Booking.BookingStatus> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setPrefWidth(150);

        TableColumn<Booking, Timestamp> colTime = new TableColumn<>("Created Time");
        colTime.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        colTime.setPrefWidth(210);

        tbvData.getColumns().addAll(colBID, colCID, colStatus, colIn, colOut, colTime);
    }

    public void loadTbvData() {
        try {
            BookingServices bS = new BookingServices();
            CustomerServices cS = new CustomerServices();
            Customer c = cS.getCustomerData(txtfInputID.getText());
            this.tbvData.setItems(FXCollections.observableList(bS.getBookingListByCusID(c.getCustomerID())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
