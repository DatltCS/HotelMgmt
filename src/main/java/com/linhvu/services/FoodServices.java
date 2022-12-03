/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhvu.services;

import com.linhvu.conf.JdbcUtils;
import com.linhvu.pojo.Food;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prodi
 */
public class FoodServices {
    public List<String> getFoodNameList() {
        List<String> foods = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT FoodName FROM food");

            while (rs.next()) {
                foods.add(rs.getString("FoodName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

//    public List<Food> getFoodList() {
//        List<Food> foods = new ArrayList<>();
//        try (Connection conn = JdbcUtils.getConn()) {
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery("SELECT * FROM food");
//
//            while (rs.next()) {
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return foods;
//    }

    public BigDecimal getFoodPriceByName(String name) {
        BigDecimal price = null;
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT PricePerUnit FROM food WHERE FoodName like ?");
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                price = rs.getBigDecimal("PricePerUnit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
}
