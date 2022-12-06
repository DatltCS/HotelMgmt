/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.test;

import com.linhvu.services.BookingRoomServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author prodi
 */
public class BookingRoomTester {
    BookingRoomServices brS = new BookingRoomServices();

    @ParameterizedTest
    @CsvSource({"10024, 101", "10005, 204"})
    public void testGetRoomByValidBookID(int bookID, int roomID) {
        Assertions.assertEquals(roomID, brS.getRoomByBookingID(bookID).getRoomID());
    }

    @ParameterizedTest
    @CsvSource({"100000", "20040"})
    public void testGetRoomByInvalidBookID(int bookID) {
        Assertions.assertNull(brS.getRoomByBookingID(bookID));
    }
}
