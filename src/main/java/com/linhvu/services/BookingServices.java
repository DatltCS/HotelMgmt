/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Booking;

import java.sql.*;

/**
 *
 * @author prodi
 *
 */
// Service class for Booking
public class BookingServices {
    public static Booking booking = new Booking();

    public int getMaxBookingID() {
        int max = 0;
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT BookingID " +
                    "FROM booking " +
                    "ORDER BY BookingID DESC " +
                    "LIMIT 1");

            while (rs.next())
                max = rs.getInt("BookingID") + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return max;
    }

    public boolean addNewBooking(Booking book) {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO booking (CreateDate, StartDate, EndDate, CustomerID) " +
                                                        "VALUES(?, ?, ?, ?)");
            stm.setTimestamp(1, book.getCreateDate());
            stm.setDate(2, Date.valueOf(book.getStateDate()));
            stm.setDate(3, Date.valueOf(book.getEndDate()));
            stm.setInt(4, CustomerServices.customer.getCustomerID());

            if (stm.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
