package com.codecool.petshop.dao;

import com.codecool.petshop.model.Food;
import com.codecool.petshop.model.FoodType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoJdbc implements FoodDao {
    DataSource dataSource;

    public FoodDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Food> findFoodByType(FoodType foodType) {
        String SQL = "SELECT * FROM pet_table " +
                "WHERE food_type = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, foodType.toString());
            ps.executeQuery();
            ResultSet res = ps.getResultSet();
            List<Food> foodList = new ArrayList<>();
            while (res.next()) {
                Food food = Food.createFood(
                        res.getLong("id"),
                        res.getDate("expiration"),
                        res.getString("name"),
                        res.getString("brand"),
                        FoodType.getEnum(res.getString("food_type"))
                );
                foodList.add(food);
            }
            return foodList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Food> findFoodByBrand(String brandName) {
        String SQL = "SELECT * FROM pet_table " +
                "WHERE brand = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, brandName);
            ps.executeQuery();
            ResultSet res = ps.getResultSet();
            List<Food> foodList = new ArrayList<>();
            while (res.next()) {
                Food food = Food.createFood(
                        res.getLong("id"),
                        res.getDate("expiration"),
                        res.getString("name"),
                        res.getString("brand"),
                        FoodType.getEnum(res.getString("food_type"))
                );
                foodList.add(food);
            }
            return foodList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
