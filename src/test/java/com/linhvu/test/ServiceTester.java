/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.test;

import com.linhvu.pojo.Service;
import com.linhvu.services.ServiceServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author prodi
 */
public class ServiceTester {
    ServiceServices sS = new ServiceServices();

    @Test
    public void testGetServicesName() {
        try {
            List<String> names = sS.getServiceNameList();
            Assertions.assertEquals(10, names.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetServicesList() {
        try {
            List<Service> services = sS.getServiceList();
            Assertions.assertEquals(10, services.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/service_data01.csv")
    public void getServicePriceByName(String name, BigDecimal price) {
        Assertions.assertEquals(price, sS.getServiceByName(name).getPricePerHour());
    }
}
