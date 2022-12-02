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
    public boolean addAccountData(Account acc) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO accounts (UserID, UserPass, AccountType, AccountStatus) VALUES(?, ?, ?, ?)");
            stm.setString(1, acc.getUserID());
            stm.setString(2, acc.getUserPass());
            stm.setString(3, "customer");
            stm.setString(4, "active");

            int rs = stm.executeUpdate();
            if (rs == 0)
                return false;
            return true;
        }
    }

    public boolean validateUserID(String userID) throws SQLException {
        // Quy ước: true (==0) -> UserID hợp lệ (chưa đc đki), false -> UserID đã đc đăng ký, ko hợp lệ
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(1) FROM accounts WHERE UserID = ?");
            stm.setString(1, userID);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
        return false;
    }

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

    public int validatePassword(String pass) {
        // Quy ước: 1 -> mk hợp lệ, 0 -> mk có khoảng trắng, -1 -> mk quá ngắn hoặc quá dài, -2 -> mk không có đủ ký tự (thường, hoa, số, ký tự đặc biệt)
        if (pass.length() < 8 || pass.length() > 16)
            return -1;
        if (pass.contains(" "))
            return 0;

        int upChar = 0, lowChar = 0, digit = 0, special = 0;
        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);
            if (Character.isUpperCase(c))
                upChar++;
            else if (Character.isLowerCase(c))
                lowChar++;
            else if (Character.isDigit(c))
                digit++;
            else
                special++;
        }

        if (upChar == 0 || lowChar == 0 || digit == 0 || special == 0)
            return -2;
        else
            return 1;
    }
}
