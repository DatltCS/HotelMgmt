/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.conf;

import com.linhvu.hotelmgmt.*;
import com.linhvu.pojo.Employee;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 *
 * @author prodi
 */
public class Utils {
    public static Alert getBox(String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(content);

        return alert;
    }

    // Xử lý đưa ứng dụng về giữa màn hình người dùng
    public static void centerScreen(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void loadCustomerMenuItem(MenuButton menuBtn) {
        // Xử lý menu người dùng
        MenuItem itemBook = new MenuItem("Book a room");
        MenuItem itemServices = new MenuItem("Room services");
        MenuItem itemSignOut = new MenuItem("Sign out");

        menuBtn.getItems().add(itemBook);
        menuBtn.getItems().add(itemServices);
        menuBtn.getItems().add(itemSignOut);

        // Xử lý các Menu button item => sự kiện click
        itemBook.setOnAction(event -> {
            try {
                App.setRoot("FMain");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        itemServices.setOnAction(event -> {
            try {
                App.setRoot("FServices");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        itemSignOut.setOnAction(event -> {
            Stage stage = (Stage)menuBtn.getScene().getWindow();
            try {
                App.setRoot("FLogin");
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.sizeToScene();
            Utils.centerScreen(stage);
        });
    }

    public static void loadEmployeeMenuItem(MenuButton menuBtn, Employee employee) {
        // Xử lý menu người dùng
        MenuItem itemMain = new MenuItem("Employee main page");
        MenuItem itemGuest = new MenuItem("Check in/out guest");
        MenuItem itemBooking = new MenuItem("Check/Edit booking details");
//        MenuItem itemRoom = new MenuItem("Manage rooms");
        MenuItem itemSignOut = new MenuItem("Sign out");

        menuBtn.getItems().add(itemMain);
        menuBtn.getItems().add(itemGuest);
        menuBtn.getItems().add(itemBooking);
//        menuBtn.getItems().add(itemRoom);
        menuBtn.getItems().add(itemSignOut);

        itemMain.setOnAction(event -> {
            try {
                App.setRoot("FMainEmployee");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Check in, check out guest
        itemGuest.setOnAction(event -> {
            try {
                App.setRoot("FCheckin");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Add, delete a service from customer booking
        itemBooking.setOnAction(event -> {
            try {
                App.setRoot("FServicesEmployee");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        itemSignOut.setOnAction(event -> {
            Stage stage = (Stage)menuBtn.getScene().getWindow();
            try {
                App.setRoot("FLogin");
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.sizeToScene();
            Utils.centerScreen(stage);
        });
    }

    // Chỉ cho phép chọn ngày bắt đầu từ hiện tại (không được chọn ngày trong quá khứ)
    public static void initDatePicker(DatePicker dtParam) {
        dtParam.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(b || localDate.compareTo(LocalDate.now()) < 0);
            }
        });

        // set format của ngày tháng năm về định dạng dd/MMM/yyyy
        dtParam.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    public static Date convertDPtoDate(DatePicker dtPicker) {
        return Date.from(dtPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//        return Date.from(Instant.from(dtPicker.getValue()));
    }

    public static LocalDate convertDatetoDP(Date date) {
        return new LocalDateStringConverter(FormatStyle.MEDIUM).fromString(date.toString());
    }
}
