/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
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
    public void addNewBookingService(int bookingID, int serviceID) {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT IGNORE INTO booking_services (BookingID, ServiceID) " +
                    "VALUES (?, ?)");
            stm.setInt(1, bookingID);
            stm.setInt(2, serviceID);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean removeBookingService(int bookingID, int serviceID) {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM booking_services WHERE BookingID = ? AND ServiceID = ?");
            stm.setInt(1, bookingID);
            stm.setInt(2, serviceID);

            if (stm.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeAllService(int bookingID) {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM booking_services WHERE BookingID = ?");
            stm.setInt(1, bookingID);

            if (stm.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
