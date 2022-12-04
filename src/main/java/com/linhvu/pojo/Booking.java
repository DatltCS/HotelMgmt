/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.pojo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author prodi
 */
public class Booking {
    public enum BookingStatus {
        booked, 
        checkedin,
        checkedout,
        canceled,
        abandoned
    }
    
    private int bookingID;
    private Timestamp createDate;
    private LocalDate stateDate;
    private LocalDate endDate;
    private BookingStatus status;
    private int customerID;

    public Booking() {
    }

    public Booking(LocalDate stateDate, LocalDate endDate) {
        this.stateDate = stateDate;
        this.endDate = endDate;
    }

    public Booking(int bookingID, Timestamp createDate, LocalDate stateDate, LocalDate endDate, int customerID) {
        this.bookingID = bookingID;
        this.createDate = createDate;
        this.stateDate = stateDate;
        this.endDate = endDate;
        this.customerID = customerID;
    }

    /**
     * @return the bookingID
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * @param bookingID the bookingID to set
     */
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the stateDate
     */
    public LocalDate getStateDate() {
        return stateDate;
    }

    /**
     * @param stateDate the stateDate to set
     */
    public void setStateDate(LocalDate stateDate) {
        this.stateDate = stateDate;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the status
     */
    public BookingStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
