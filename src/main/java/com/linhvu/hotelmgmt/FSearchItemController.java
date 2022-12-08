/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.linhvu.hotelmgmt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.linhvu.conf.Utils;
import com.linhvu.pojo.Room;
import com.linhvu.services.BookingServices;
import com.linhvu.services.RoomServices;
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
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author prodi
 */
public class FSearchItemController implements Initializable {
    @FXML Label lbRoomNum;
    @FXML Label lbRoomName;
    @FXML Label lbPrice;
    @FXML Text txtDescription;
    @FXML Button btnRoom;

    private Room room;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnRoom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (BookingServices.booking.getStateDate() != null && BookingServices.booking.getEndDate() != null) {
                    RoomServices.room = room;

                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FBooking.fxml"));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);

                    // điều chỉnh không cho phép tương tác với login khi đang mở sign-up
                    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } else
                    Utils.getBox("Please choose both StartDate and EndDate first!", Alert.AlertType.INFORMATION).show();
            }
        });
    }

    public void setData(Room r) {
        this.room = r;
        lbRoomNum.setText(String.valueOf(r.getRoomID()));
        lbRoomName.setText(r.getRoomName());
        lbPrice.setText(String.valueOf(r.getPricePerDay()));
        txtDescription.setText(r.getDescription());
    }
}
