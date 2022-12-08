/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.test;

import com.linhvu.pojo.Account;
import com.linhvu.pojo.Customer;
import com.linhvu.services.AccountServices;
import com.linhvu.services.CustomerServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author prodi
 */
public class CustomerTester {
    CustomerServices cS = new CustomerServices();
    AccountServices aS = new AccountServices();

    @ParameterizedTest
    @Tag("getCustomerData")
    @CsvFileSource(resources = "/cus_data01.csv")
    public void getValidCustomerData(String userID, String userName, String phone) {
        try {
            Customer c = cS.getCustomerData(userID);

            Assertions.assertEquals(userName, c.getfName());
            Assertions.assertEquals(phone, c.getPhoneNum());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @Tag("getCustomerData")
    @CsvSource({"040302010000", "881345", "466332040979294912", "abchdkskic"})
    public void getInvalideCustomerData(String userID) {
        try {
            Assertions.assertNull(cS.getCustomerData(userID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Tag("addCustomerData")
    public void addValidCustomerData() throws SQLException {
        Random rand;
        String testID;
        do {
            rand = new Random();
            testID = (int)(rand.nextDouble() * 1000) + "000000000";
        } while (!aS.validateUserID(testID));

        Account a = new Account(testID, "Testing@123", Account.AccountType.customer);
        Customer c = new Customer(testID, "Test", "Unit", LocalDate.of(2000, 10, 1), "0900000000");

        aS.addAccountData(a);
        cS.addCustomerData(c);

        Customer customer = cS.getCustomerData(testID);
        Assertions.assertEquals(testID, customer.getUserID());
        Assertions.assertEquals(c.getfName(), customer.getfName());
        Assertions.assertEquals(c.getlName(), customer.getlName());
    }

    @Test
    @Tag("addCustomerData")
    public void addInvalidCustomerData() {
        Customer c = new Customer("040302011111", "Test", "Unit", LocalDate.of(2000, 10, 1), "0900000000");
        try {
            cS.addCustomerData(c);

            Customer customer = cS.getCustomerData(c.getUserID());
            Assertions.assertEquals(c.getUserID(), customer.getUserID());
            Assertions.assertEquals(c.getfName(), customer.getfName());
            Assertions.assertEquals(c.getlName(), customer.getlName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @Tag("vadidatePhoneNum")
    @CsvSource({"0393518039", "0878053242"})
    public void testValidPhoneNum(String phone) {
        Assertions.assertTrue(cS.vadidatePhoneNum(phone));
    }

    @ParameterizedTest
    @Tag("vadidatePhoneNum")
    @CsvSource({"abcshencid", "9381959120", "01234", "0195812928393"})
    public void testInvalidPhoneNum(String phone) {
        Assertions.assertFalse(cS.vadidatePhoneNum(phone));
    }
}
