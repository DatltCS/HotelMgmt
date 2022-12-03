/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Booking;
import com.linhvu.pojo.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author prodi
 */

// Service class for BookingService
public class BookingSServices {
    public void addNewBookingService(Booking booking, Service service) {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO booking_services (BookingID, ServiceID) " +
                    "VALUES (?, ?)");
            stm.setInt(1, booking.getBookingID());
            stm.setInt(2, service.getServiceID());

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
