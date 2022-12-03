/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Customer;

import java.sql.*;

/**
 *
 * @author prodi
 */
public class CustomerServices {
    public static Customer customer = new Customer();

    public Customer getCustomerData(String userID) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM customers WHERE UserID = ?");
            stm.setString(1, userID);

            ResultSet rs = stm.executeQuery();

            Customer c = null;
            while (rs.next()) {
                c = new Customer(userID,
                        rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getDate("Birthday").toLocalDate(), rs.getString("PhoneNum"),
                        rs.getString("Email"), rs.getString("Address"));
                c.setCustomerID(rs.getInt("CustomerID"));
            }
            return c;
        }
    }

    public boolean addCustomerData(Customer c) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            Date bday = Date.valueOf(c.getBirthday());
            PreparedStatement stm = conn.prepareStatement("INSERT INTO customers (UserID, FirstName, LastName, Birthday, PhoneNum) " +
                                                                "VALUES(?, ?, ?, ?, ?)");
            stm.setString(1, c.getUserID());
            stm.setString(2, c.getfName());
            stm.setString(3, c.getlName());
            stm.setDate(4, bday);
            stm.setString(5, c.getPhoneNum());

            int rs = stm.executeUpdate();
            if (rs == 0)
                return false;
            return true;
        }
    }
}
