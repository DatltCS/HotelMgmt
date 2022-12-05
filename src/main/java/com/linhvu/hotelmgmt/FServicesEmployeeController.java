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
import com.linhvu.pojo.Booking;
import com.linhvu.pojo.Service;
import com.linhvu.services.BookingSServices;
import com.linhvu.services.BookingServices;
import com.linhvu.services.EmployeeServices;
import com.linhvu.services.ServiceServices;
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
public class FServicesEmployeeController implements Initializable {
    @FXML TextField txtfID;
    @FXML Button btnSearch;
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

        loadTableView(tbvServices);
        loadTableView(tbvChosen);

        loadTBVServicesData();
    }

    public void btnSearchClick(ActionEvent event) {
        if (!validateTxtfID())
            tbvChosen.getItems().clear();
        else
            loadTBVChosenData();
    }

    public void btnAddClick(ActionEvent event) {
        if (validateTxtfID()) {
            Service s = tbvServices.getSelectionModel().getSelectedItem();
            if (s == null)
                Utils.getBox("Please choose the service you want first!", Alert.AlertType.WARNING).show();
            else {
                // Them service vao booking da chon
                BookingSServices bsS = new BookingSServices();
                bsS.addNewBookingService(Integer.parseInt(txtfID.getText()), s.getServiceID());
                // Reload tbvChosen
                loadTBVChosenData();
            }
        }
    }

    public void btnDeleteClick(ActionEvent event) {
        if (validateTxtfID()) {
            Service s = tbvChosen.getSelectionModel().getSelectedItem();
            if (s == null)
                Utils.getBox("Please choose the service you want to delete", Alert.AlertType.WARNING).show();
            else {
                // Xoa service ra khoi booking da chon
                BookingSServices bsS = new BookingSServices();
                if (bsS.removeBookingService(Integer.parseInt(txtfID.getText()), s.getServiceID()))
                    loadTBVChosenData();
            }
        }
    }

    public void btnDeleteAllClick(ActionEvent event) {
        if (validateTxtfID()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Delete all services this booking have?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                // Thuc hien xoa het tat ca dich vu
                BookingSServices bsS = new BookingSServices();
                if (bsS.removeAllService(Integer.parseInt(txtfID.getText())))
                    loadTBVChosenData();
            }
        }
    }

    public boolean validateTxtfID() {
        if (txtfID.getText().trim().length() == 0) {
            Utils.getBox("Insert Booking ID first!", Alert.AlertType.WARNING).show();
            return false;
        }
        else if (!Utils.isInterger(txtfID.getText())) {
            Utils.getBox("Not a valid Booking ID!", Alert.AlertType.ERROR).show();
            return false;
        }
        else {
            BookingServices bS = new BookingServices();
            Booking b = bS.getBookingByID(Integer.parseInt(this.txtfID.getText()));
            if (b == null) {
                Utils.getBox("This Booking ID does not exist!", Alert.AlertType.ERROR).show();
                return false;
            }
            else {
                return true;
            }
        }
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
        this.tbvChosen.setItems(FXCollections.observableList(bsS.getBookedService(Integer.parseInt(this.txtfID.getText()))));
    }
}
