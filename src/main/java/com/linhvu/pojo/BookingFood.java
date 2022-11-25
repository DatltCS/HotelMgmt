/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.pojo;

import java.math.BigDecimal;

/**
 *
 * @author prodi
 */
public class BookingFood {
    private int bookingID;
    private int foodID;
    private int quantity;
    private BigDecimal totalBill;

    public BookingFood() {
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
     * @return the foodID
     */
    public int getFoodID() {
        return foodID;
    }

    /**
     * @param foodID the foodID to set
     */
    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the totalBill
     */
    public BigDecimal getTotalBill() {
        return totalBill;
    }

    /**
     * @param totalBill the totalBill to set
     */
    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }
}
