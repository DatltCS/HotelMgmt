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
import java.util.ArrayList;
import java.util.List;

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


}
