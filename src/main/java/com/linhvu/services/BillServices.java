/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.pojo.Booking;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author prodi
 */
public class BillServices {
    public BigDecimal calRoomPrice(Booking booking) {
        long days = ChronoUnit.DAYS.between(booking.getStateDate(), booking.getEndDate());
        BigDecimal roomPrice;
        roomPrice = RoomServices.room.getPricePerDay().multiply(BigDecimal.valueOf(days + 1));
        return roomPrice;
    }
}
