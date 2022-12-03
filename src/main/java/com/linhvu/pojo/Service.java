/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author prodi
 */
public class Service {
    private int serviceID;
    private String serviceName;
    private BigDecimal pricePerHour;
    private Date isDeleted;

    public Service() {
    }

    public Service(int serviceID, String serviceName, BigDecimal pricePerHour, Date isDeleted) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.pricePerHour = pricePerHour;
        this.isDeleted = isDeleted;
    }

    /**
     * @return the serviceID
     */
    public int getServiceID() {
        return serviceID;
    }

    /**
     * @param serviceID the serviceID to set
     */
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the pricePerHour
     */
    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    /**
     * @param pricePerHour the pricePerHour to set
     */
    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    /**
     * @return the isDeleted
     */
    public Date getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(Date isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    
}
