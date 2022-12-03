/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prodi
 */
public class RoomServices {
    public List<Room> getRoomList(LocalDate start, LocalDate end) throws SQLException {
        if (start == null && end == null)
            return null;

        List<Room> rooms = new ArrayList<>();

        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm;
            if (start == null) {
                stm = conn.prepareStatement("select * " +
                        "from room " +
                        "where RoomID not in (select RoomID " +
                            "from booking b join booking_room br on b.BookingID = br.BookingID " +
                            "where (? between StartDate and EndDate))" +
                        "group by RoomID");
                stm.setDate(1, Date.valueOf(end));
            } else if (end == null) {
                stm = conn.prepareStatement("select * " +
                        "from room " +
                        "where RoomID not in (select RoomID " +
                            "from booking b join booking_room br on b.BookingID = br.BookingID " +
                            "where (? between StartDate and EndDate))" +
                        "group by RoomID");
                stm.setDate(1, Date.valueOf(start));
            } else {
                stm = conn.prepareStatement("select * " +
                        "from room " +
                        "where RoomID not in (select RoomID " +
                            "from booking b join booking_room br on b.BookingID = br.BookingID " +
                            "where (StartDate between ? and ?) " +
                                "or (EndDate between ? and ?))" +
                        "group by RoomID");
                stm.setDate(1, Date.valueOf(start));
                stm.setDate(2, Date.valueOf(end));
                stm.setDate(3, Date.valueOf(start));
                stm.setDate(4, Date.valueOf(end));
            }

            Room r = null;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                r = new Room(rs.getInt("RoomID"), rs.getString("RoomName"),
                        rs.getBigDecimal("PricePerDay"), rs.getBoolean("AllowSmoking"),
                        rs.getInt("SingleBed"), rs.getInt("DoubleBed"), rs.getString("Description"));
                rooms.add(r);
            }
        }
        return rooms;
    }
}
