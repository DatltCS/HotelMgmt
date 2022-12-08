/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.test;

import com.linhvu.pojo.Account;
import com.linhvu.services.AccountServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author prodi
 */
public class AccountTester {
    AccountServices aS = new AccountServices();

    @Test
    @Tag("addAccountData")
    public void addValidAccount() throws SQLException {
        Random rand;
        String testID;
        do {
            rand = new Random();
            testID = (int)(rand.nextDouble() * 1000) + "000000000";
        } while (!aS.validateUserID(testID));

        Account a = new Account(testID, "Testing@123", Account.AccountType.customer);

        // Nếu thêm thành công, hàm validateLogin sẽ trả về giá trị true -> account hợp lệ và đã được thêm
        try {
            aS.addAccountData(a);
            Assertions.assertTrue(aS.validateLogin(a));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Tag("addAccountData")
    public void addInvalidAccount() throws SQLException {
        Account a = new Account("040302015348", "Testing@123", Account.AccountType.customer);

        // Khi thêm thất bại, hàm validateLogin sẽ trả về giá trị false -> account chưa tồn tại trong hệ thống
        try {
            aS.addAccountData(a);
            Assertions.assertFalse(aS.validateLogin(a));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Tag("validateUserID")
    public void testValidUserID() {
        String id = "128482920483";
        try {
            Assertions.assertTrue(aS.validateUserID(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @Tag("validateUserID")
    @ValueSource(strings = {"1234587", "23958293028509", "abcdefg12345", "040302010000"})
    public void testInvalidUserID(String id) {
        try {
            Assertions.assertFalse(aS.validateUserID(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @Tag("validateLogin")
    @CsvSource({"040302010000, m3lik3milk@A, employee"})
    public void testValidLogin(String accID, String accPass, Account.AccountType type) {
        Account a = new Account(accID, accPass, type);
        try {
            Assertions.assertTrue(aS.validateLogin(a));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @Tag("validateLogin")
    @CsvSource({"040302010000, m3lik3milk@A, customer", "040302011111, randompass, customer", "123456789, Random@123, customer"})
    public void testInvalidLogin(String accID, String accPass, Account.AccountType type) {
        Account a = new Account(accID, accPass, type);
        try {
            Assertions.assertFalse(aS.validateLogin(a));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Quy ước: 1 -> mk hợp lệ,
    // 0 -> mk có khoảng trắng, -1 -> mk quá ngắn hoặc quá dài, -2 -> mk không có đủ ký tự (thường, hoa, số, ký tự đặc biệt)
    @ParameterizedTest
    @Tag("validatePassword")
    @CsvSource({"Random@123, 1", "SuperString@123, 1"})
    public void testValidPassword(String pass, int key) {
        Assertions.assertEquals(key, aS.validatePassword(pass));
    }

    @ParameterizedTest
    @Tag("validatePassword")
    @CsvSource({"M@t Khau123, 0", "P@ss123, -1", "SuperStringP@ss123, -1", "simplepass123, -2", "2SimplePass, -2"})
    public void testInvalidPassword(String pass, int key) {
        Assertions.assertEquals(key, aS.validatePassword(pass));
    }
}
