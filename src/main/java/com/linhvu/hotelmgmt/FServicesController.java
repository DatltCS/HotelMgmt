/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Service;
import com.linhvu.services.*;
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
public class FServicesController implements Initializable {
    @FXML ComboBox<Integer> cbRoomNum;
    @FXML MenuButton menuBtn;
    @FXML TableView<Service> tbvServices;
    @FXML TableView<Service> tbvChosen;
    @FXML Button btnAdd;
    @FXML Button btnDelete;
    @FXML Button btnDeleteAll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ((long) menuBtn.getItems().size() < 1)
            loadMenuButton(true);

        BookingServices bS = new BookingServices();
        this.cbRoomNum.setItems(FXCollections.observableList(bS.getBookingIDList(CustomerServices.customer.getCustomerID())));

        // load hiển thị các table view
        loadTableView(tbvServices);
        loadTableView(tbvChosen);

        // load data các table view
        loadTBVServicesData();
        cbRoomNum.setOnAction(event -> {
            loadTBVChosenData();
        });
    }

    public void btnAddClick(ActionEvent event) {
        if (cbRoomNum.getValue() == null)
            Utils.getBox("Please choose your booking ID!", Alert.AlertType.WARNING).show();
        else {
            Service s = tbvServices.getSelectionModel().getSelectedItem();
            if (s == null)
                Utils.getBox("Please choose the service you want first!", Alert.AlertType.WARNING).show();
            else {
                // Them service vao booking da chon
                BookingServices bS = new BookingServices();
                BookingSServices bsS = new BookingSServices();
                bsS.addNewBookingService(bS.getBookingByID(this.cbRoomNum.getValue()), s);
                // Reload tbvChosen
                loadTBVChosenData();
            }
        }
    }

    public void btnDeleteClick(ActionEvent event) {
        if (cbRoomNum.getValue() == null)
            Utils.getBox("Please choose your booking ID!", Alert.AlertType.WARNING).show();
        else {
            Service s = tbvChosen.getSelectionModel().getSelectedItem();
            if (s == null)
                Utils.getBox("Please choose the service you want to delete", Alert.AlertType.WARNING).show();
            else {
                // Xoa service ra khoi booking da chon

                // Reload tbvChosen
                loadTBVChosenData();
            }
        }
    }

    public void btnDeleteAllClick(ActionEvent event) {
        if (cbRoomNum.getValue() == null)
            Utils.getBox("Please choose your booking ID!", Alert.AlertType.WARNING).show();
        else {
            Alert alert = Utils.getBox("Delete all services that you have?", Alert.AlertType.CONFIRMATION);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                // Thuc hien xoa het tat ca dich vu

                // Reload tbvChosen
                loadTBVChosenData();
            }
        }
    }

    public void loadMenuButton(boolean key) {
        // key -> đánh dấu đã xác thực đăng nhập hay chưa
        if (key) {
            this.menuBtn.setText("Welcome, " + CustomerServices.customer.getfName());
            Utils.loadCustomerMenuItem(menuBtn);
        } else
            this.menuBtn.setText("TESTING");
    }

    public void loadTableView(TableView<Service> tbv) {
        TableColumn<Service, Integer> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
        colID.setPrefWidth(40);

        TableColumn<Service, String> colName = new TableColumn<>("Service Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        colName.setPrefWidth(270);

        TableColumn<Service, BigDecimal> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("pricePerHour"));
        colPrice.setPrefWidth(140);

        tbv.getColumns().addAll(colID, colName, colPrice);
    }

    public void loadTBVServicesData() {
        ServiceServices sS = new ServiceServices();
        try {
            this.tbvServices.setItems(FXCollections.observableList(sS.getServiceList()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadTBVChosenData() {
        BookingSServices bsS = new BookingSServices();
        this.tbvChosen.setItems(FXCollections.observableList(bsS.getBookedService(this.cbRoomNum.getValue())));
    }
}
