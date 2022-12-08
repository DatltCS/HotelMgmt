package com.linhvu.test;

import com.linhvu.pojo.Employee;
import com.linhvu.services.EmployeeServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;

public class EmployeeTester {
    EmployeeServices eS = new EmployeeServices();

    @ParameterizedTest
    @Tag("getEmployeeData")
    @CsvFileSource(resources = "/emp_data.csv")
    public void getValidEmployeeData(String userID, String fName, String lName, String phone) {
        try {
            Employee e = eS.getEmployeeData(userID);

            Assertions.assertEquals(fName, e.getfName());
            Assertions.assertEquals(lName, e.getlName());
            Assertions.assertEquals(phone, e.getPhoneNum());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @Tag("getEmployeeData")
    @CsvSource({"040302015348", "881345", "466332040979294912", "abchdkskic"})
    public void getInvalideEmployeeData(String userID) {
        try {
            Assertions.assertNull(eS.getEmployeeData(userID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
