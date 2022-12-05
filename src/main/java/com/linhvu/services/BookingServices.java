/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public Booking getBookingByID(int bookingID) {
        Booking b = null;
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM booking WHERE BookingID = ? ");
            stm.setInt(1, bookingID);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                b = new Booking(rs.getInt("BookingID"), rs.getTimestamp("CreateDate"),
                        rs.getDate("StartDate").toLocalDate() , rs.getDate("EndDate").toLocalDate(),
                        rs.getInt("CustomerID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public List<Integer> getBookingIDList(int customerID) {
        List<Integer> IDList = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT BookingID FROM booking WHERE CustomerID = ? " +
                    "AND Status <> 'canceled' AND Status <> 'abandoned' AND Status <> 'checked-out'");
            stm.setInt(1, customerID);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                IDList.add(rs.getInt("BookingID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return IDList;
    }

    public List<Booking> getBookingListByCusID(int customerID) {
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM booking WHERE CustomerID = ?");
            stm.setInt(1, customerID);

            ResultSet rs = stm.executeQuery();
            Booking b = null;
            while (rs.next()) {
                b = new Booking(rs.getInt("BookingID"), rs.getTimestamp("CreateDate"),
                        rs.getDate("StartDate").toLocalDate() , rs.getDate("EndDate").toLocalDate(),
                        rs.getInt("CustomerID"));
                switch (rs.getString("Status")) {
                    case "booked":
                        b.setStatus(Booking.BookingStatus.booked);
                        break;
                    case "canceled":
                        b.setStatus(Booking.BookingStatus.canceled);
                        break;
                    case "abandoned":
                        b.setStatus(Booking.BookingStatus.abandoned);
                        break;
                    case "checked-in":
                        b.setStatus(Booking.BookingStatus.checkedin);
                        break;
                    case "checked-out":
                        b.setStatus(Booking.BookingStatus.checkedout);
                        break;
                }
                bookings.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public Booking getValidBookingByID (int bookingID) {
        Booking b = null;
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM booking WHERE BookingID = ? " +
                    "AND Status <> 'canceled' AND Status <> 'abandoned' AND Status <> 'checked-out'");
            stm.setInt(1, bookingID);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                b = new Booking(rs.getInt("BookingID"), rs.getTimestamp("CreateDate"),
                        rs.getDate("StartDate").toLocalDate() , rs.getDate("EndDate").toLocalDate(),
                        rs.getInt("CustomerID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean updateBookingStatus(Booking book, Booking.BookingStatus status) {
        if (book.getStatus() == Booking.BookingStatus.checkedout || book.getStatus() == Booking.BookingStatus.canceled) {
            return false;
        } else if (book.getStatus() == status) {
            return false;
        } else if (book.getStatus() == Booking.BookingStatus.booked && status == Booking.BookingStatus.checkedout) {
            return false;
        } else {
            String bStatus;
            switch (status) {
                case checkedin:
                    bStatus = "checked-in";
                    break;
                case checkedout:
                    bStatus = "checked-out";
                    break;
                default:
                    bStatus = status.toString();
                    break;
            }

            try (Connection conn = JdbcUtils.getConn()) {
                PreparedStatement stm = conn.prepareStatement("UPDATE booking SET Status = ? WHERE BookingID = ?");
                stm.setString(1, bStatus);
                stm.setInt(2, book.getBookingID());

                if (stm.executeUpdate() != 0)
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void updateBookingDate(Booking book, Integer key) {
        // key: 1 = update ngay check-in thanh ngay hien tai
        //      2 = update ngay check-out thanh ngay hien tai
        LocalDate today = LocalDate.now();

        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = null;
            if (key == 1)
                stm = conn.prepareStatement("UPDATE booking SET StartDate = ? WHERE BookingID = ?");
            else if (key == 2)
                stm = conn.prepareStatement("UPDATE booking SET EndDate = ? WHERE BookingID = ?");

            stm.setDate(1, Date.valueOf(today));
            stm.setInt(2, book.getBookingID());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
