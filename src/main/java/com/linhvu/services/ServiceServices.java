/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prodi
 */
public class ServiceServices {
    public List<String> getServiceNameList() throws SQLException {
        List<String> services = new ArrayList<>();

        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Name FROM services");

            while (rs.next()) {
                services.add(rs.getString("Name"));
            }
        }
        return services;
    }

    public List<Service> getServiceList() throws SQLException {
        List<Service> services = new ArrayList<>();

        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM services");

            Service s = null;
            while (rs.next()) {
                s = new Service(rs.getInt("ServiceID"), rs.getString("Name"), rs.getBigDecimal("ChargePerHour"), rs.getDate("IsDeleted"));
                services.add(s);
            }
        }
        return services;
    }
}
