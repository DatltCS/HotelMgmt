/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Booking;
import com.linhvu.services.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FBookingController implements Initializable {
    @FXML Text txtRoom;
    @FXML Text txtName;
    @FXML Text txtCheckin;
    @FXML Text txtCheckout;
    @FXML Text txtPrice;
    @FXML CheckComboBox<String> cbServices;
    @FXML Text txtTotal;
    @FXML Button btnFinish;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtRoom.setText(String.valueOf(RoomServices.room.getRoomID()));
        txtName.setText(RoomServices.room.getRoomName());
        txtCheckin.setText(BookingServices.booking.getStateDate().toString());
        txtCheckout.setText(BookingServices.booking.getEndDate().toString());
        txtPrice.setText(String.valueOf(RoomServices.room.getPricePerDay()));
        txtTotal.setText(String.valueOf(calTotal()));

        ServiceServices sS = new ServiceServices();
        try {
            ObservableList<String> servicesName = FXCollections.observableArrayList(sS.getServiceNameList());
            cbServices.getItems().addAll(servicesName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cbServices.getCheckModel().getCheckedIndices().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                txtTotal.setText(String.valueOf(calTotal()));
            }
        });
    }

    public void btnFinishClick(ActionEvent event) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        BookingServices bS = new BookingServices();
        BookingServices.booking.setBookingID(bS.getMaxBookingID());

        BookingServices.booking.setCreateDate(time);
        BookingServices.booking.setStatus(Booking.BookingStatus.booked);
        BookingServices.booking.setCustomerID(CustomerServices.customer.getCustomerID());

        if (bS.addNewBooking(BookingServices.booking, CustomerServices.customer.getCustomerID())) {
            BookingRoomServices rS = new BookingRoomServices();
            rS.addNewBookingRoom(BookingServices.booking, RoomServices.room);

            BookingSServices bsS = new BookingSServices();
            ServiceServices sS = new ServiceServices();
            List<String> services = cbServices.getCheckModel().getCheckedItems();
            if (services.size() != 0) {
                for (String s:services) {
                    bsS.addNewBookingService(BookingServices.booking.getBookingID(), sS.getServiceByName(s).getServiceID());
                }
            }
            Utils.getBox("Booking success!", Alert.AlertType.INFORMATION).show();

            // Đóng cửa sổ booking sau khi đăng ký thành công
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();

            // fix bug
        } else
            Utils.getBox("Booking failed!", Alert.AlertType.ERROR).show();
    }

    public BigDecimal calTotal() {
        BillServices bS = new BillServices();
        BigDecimal totalPrice = bS.calRoomPrice(BookingServices.booking);
        List<String> services = cbServices.getCheckModel().getCheckedItems();
        ServiceServices sS = new ServiceServices();

        if (services.size() != 0) {
            for (String s:services) totalPrice = totalPrice.add(sS.getServicePriceByName(s));
        }
        return totalPrice;
    }
}
