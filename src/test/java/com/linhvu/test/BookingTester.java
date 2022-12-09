package com.linhvu.test;

import com.linhvu.pojo.Booking;
import com.linhvu.services.BookingServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.sql.Timestamp;
import java.time.LocalDate;

public class BookingTester {
    BookingServices bS = new BookingServices();

    @ParameterizedTest
    @Tag("updateBookingStatus")
    @CsvFileSource(resources = "/book_status01.csv")
    public void testStatusTrans(Booking.BookingStatus before, Booking.BookingStatus after, boolean expected) {
        Booking b = new Booking();
        b.setStatus(before);
        Assertions.assertEquals(expected, bS.validStatusTrans(b, after));
    }

    @Test
    @Tag("addNewBooking")
    public void addValidBooking() {
        int countBefore = bS.getMaxBookingID();

        Booking b = new Booking(LocalDate.of(2022, 12, 24), LocalDate.of(2023, 1, 3));
        Timestamp time = new Timestamp(System.currentTimeMillis());
        b.setCreateDate(time);
        b.setCustomerID(1000);
        bS.addNewBooking(b, b.getCustomerID());

        Assertions.assertNotEquals(bS.getMaxBookingID(), countBefore);
    }
}
