/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.test;

import com.linhvu.pojo.Customer;
import com.linhvu.services.CustomerServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;

/**
 *
 * @author prodi
 */
public class CustomerTester {
    CustomerServices cS = new CustomerServices();

    @ParameterizedTest
    @CsvSource({"0393518039", "0878053242"})
    public void testValidPhoneNum(String phone) {
        Assertions.assertTrue(cS.vadidatePhoneNum(phone));
    }

    @ParameterizedTest
    @CsvSource({"abcshencid", "9381959120", "01234", "0195812928393"})
    public void testInvalidPhoneNum(String phone) {
        Assertions.assertFalse(cS.vadidatePhoneNum(phone));
    }

    @ParameterizedTest
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
    @CsvSource({"040302010000", "881345", "466332040979294912", "abchdkskic"})
    public void getInvalideCustomerData(String userID) {
        try {
            Assertions.assertNull(cS.getCustomerData(userID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
