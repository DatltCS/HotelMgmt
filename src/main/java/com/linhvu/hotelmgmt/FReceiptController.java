/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import com.linhvu.pojo.Bill;
import com.linhvu.pojo.Room;
import com.linhvu.pojo.Service;
import com.linhvu.services.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FReceiptController implements Initializable {
    @FXML Text txtCusID;
    @FXML Text txtRoomID;
    @FXML Text txtRoomName;
    @FXML Text inDate;
    @FXML Text outDate;
    @FXML Text roomPrice;
    @FXML Text totalPrice;
    @FXML TableView<Service> tbvService;
    @FXML Button btnCancel;
    @FXML Button btnConfirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTbv();
        if (BillServices.book != null) {
            loadTbvData();
            loadData();
        }
    }

    public void loadData() {
        BookingRoomServices brS = new BookingRoomServices();
        BillServices bS = new BillServices();

        Room room = brS.getRoomByBookingID(BillServices.book.getBookingID());
        BigDecimal roomCharge = bS.calRoomPrice(BillServices.book);
        BigDecimal serviceCharge = bS.calServicePrice(BillServices.book);

        txtCusID.setText(String.valueOf(BillServices.book.getCustomerID()));
        txtRoomID.setText(String.valueOf(room.getRoomID()));
        txtRoomName.setText(room.getRoomName());
        inDate.setText(BillServices.book.getStateDate().toString());
        outDate.setText(BillServices.book.getEndDate().toString());
        roomPrice.setText(String.valueOf(roomCharge));
        totalPrice.setText(String.valueOf(roomCharge.add(serviceCharge)));
    }

    public void loadTbv() {
        TableColumn<Service, Integer> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
        colID.setPrefWidth(45);

        TableColumn<Service, String> colName = new TableColumn<>("Service Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        colName.setPrefWidth(300);

        TableColumn<Service, BigDecimal> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("pricePerHour"));
        colPrice.setPrefWidth(250);

        tbvService.getColumns().addAll(colID, colName, colPrice);
    }

    public void loadTbvData() {
        BookingSServices bsS = new BookingSServices();
        tbvService.getItems().setAll(FXCollections.observableList(bsS.getBookedService(BillServices.book.getBookingID())));
    }
}
