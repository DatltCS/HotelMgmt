/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Room;
import com.linhvu.services.BookingServices;
import com.linhvu.services.CustomerServices;
import com.linhvu.services.RoomServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

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

    private List<Room> rooms = new ArrayList<>();

    RoomServices rS = new RoomServices();

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
        dtCheckin.setValue(BookingServices.booking.getStateDate());
        dtCheckout.setValue(BookingServices.booking.getEndDate());

        loadSearchItem();

        // Định dạng lại điều kiện của DatePicker mỗi khi có ngày được chọn
        dtCheckin.valueProperty().addListener((observableValue, localDate, t1) -> {
            reinitDtCheckout();
            BookingServices.booking.setStateDate(dtCheckin.getValue());
            loadSearchItem();
        });
        dtCheckout.valueProperty().addListener((observableValue, localDate, t1) -> {
            reinitDtCheckin();
            BookingServices.booking.setEndDate(dtCheckout.getValue());
            loadSearchItem();
        });
    }

    public void loadMenuButton(boolean key) {
        // key -> đánh dấu đã xác thực đăng nhập hay chưa
        if (key) {
            this.menuBtn.setText("Welcome, " + CustomerServices.customer.getfName());
            Utils.loadCustomerMenuItem(menuBtn);
        } else
            this.menuBtn.setText("TESTING");
    }

    public void loadSearchItem() {
        rooms.clear();
        try {
            rooms.addAll(rS.getRoomList(dtCheckin.getValue(), dtCheckout.getValue()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int row = 1, col = 0;
        try {
            for (int i = 0; i < rooms.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FSearchItem.fxml"));
                VBox pane = fxmlLoader.load();

                FSearchItemController itemCtrl = fxmlLoader.getController();
                itemCtrl.setData(rooms.get(i));

                if (col == 2) {
                    col = 0;
                    row++;
                }

                gpResult.add(pane, col++, row, 1, 1);
                //set grid width
                gpResult.setMinWidth(Region.USE_COMPUTED_SIZE);
                gpResult.setPrefWidth(800);
                gpResult.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gpResult.setMinHeight(Region.USE_COMPUTED_SIZE);
                gpResult.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gpResult.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(pane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
