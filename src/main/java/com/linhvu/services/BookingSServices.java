/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Booking;
import com.linhvu.pojo.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void removeBookingService(Booking booking, Service service) {
        
    }

    public List<Service> getBookedService(int bookingID) {
        List<Service> services = new ArrayList<>();

        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * " +
                    "FROM booking_services bs JOIN services s ON bs.ServiceID = s.ServiceID " +
                    "WHERE BookingID = ?");
            stm.setInt(1, bookingID);

            ResultSet rs = stm.executeQuery();
            Service s = null;
            while (rs.next()) {
                s = new Service(rs.getInt("ServiceID"), rs.getString("Name"),
                        rs.getBigDecimal("ChargePerHour"), rs.getDate("IsDeleted"));
                services.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
}
