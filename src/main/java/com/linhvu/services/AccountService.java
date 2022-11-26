/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author prodi
 */
public class AccountService {
    public boolean validateLogin(Account a) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(1) FROM accounts WHERE UserID = ? AND UserPass = ? AND AccountType = ?");
            stm.setString(1, a.getUserID());
            stm.setString(2, a.getUserPass());
            stm.setString(3, a.getType().toString());
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                return rs.getInt(1) != 0;
            }
        }
        return false;
    }
}
