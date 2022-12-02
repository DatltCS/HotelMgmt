/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.pojo;

/**
 *
 * @author prodi
 */
public class Account {
    public enum AccountType {
        customer,
        employee
    }
    
    public enum AccountStatus {
        active,
        close,
        blacklisted
    }
    
    private String userID;
    private String userPass;
    private AccountType type;
    private AccountStatus status;

    public Account() {
    }

    public Account(String userID, String userPass, AccountType type) {
        this.userID = userID;
        this.userPass = userPass;
        this.type = type;
        this.status = AccountStatus.active;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the userPass
     */
    public String getUserPass() {
        return userPass;
    }

    /**
     * @param userPass the userPass to set
     */
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    /**
     * @return the type
     */
    public AccountType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AccountType type) {
        this.type = type;
    }

    /**
     * @return the status
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
