/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.pojo;

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
    private Date createDate;
    private Date stateDate;
    private Date endDate;
    private BookingStatus status;
    private int customerID;

    public Booking() {
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
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the stateDate
     */
    public Date getStateDate() {
        return stateDate;
    }

    /**
     * @param stateDate the stateDate to set
     */
    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
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
