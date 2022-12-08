/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Booking;
import com.linhvu.pojo.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author prodi
 */
public class BookingRoomServices {
    public void addNewBookingRoom(Booking booking, Room room) {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO booking_room (BookingID, RoomID) " +
                    "VALUES (?, ?)");
            stm.setInt(1, booking.getBookingID());
            stm.setInt(2, room.getRoomID());

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Room getRoomByBookingID(int bookID) {
        Room room = null;
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM room r JOIN booking_room br on r.RoomID = br.RoomID " +
                    "WHERE br.BookingID = ? ");
            stm.setInt(1, bookID);
            ResultSet rs = stm.executeQuery();

            while (rs.next())
                room = new Room(rs.getInt("RoomID"), rs.getString("RoomName"),
                        rs.getBigDecimal("PricePerDay"), rs.getBoolean("AllowSmoking"),
                        rs.getInt("SingleBed"), rs.getInt("DoubleBed"), rs.getString("Description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
}
