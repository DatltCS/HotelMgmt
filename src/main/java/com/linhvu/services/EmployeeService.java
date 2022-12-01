/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author prodi
 */
public class EmployeeService {
    public static Employee employee = new Employee();

    public Employee getEmployeeData(String userID) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM employees WHERE UserID = ?");
            stm.setString(1, userID);

            ResultSet rs = stm.executeQuery();

            Employee e = null;
            while (rs.next()) {
                e = new Employee(rs.getInt("EmployeeID"), userID,
                        rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getDate("Birthday"), rs.getString("PhoneNum"),
                        rs.getString("Email"), rs.getString("Address"));
            }
            return e;
        }
    }
}
