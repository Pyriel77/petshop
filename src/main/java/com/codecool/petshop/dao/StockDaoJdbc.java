package com.codecool.petshop.dao;

import com.codecool.petshop.model.Stock;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDaoJdbc implements StockDao{
    private DataSource dataSource;

    public StockDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setPrice(long foodId, long newPrice) {
        String SQL = "UPDATE stock " +
                "SET price = ? " +
                "WHERE food_id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, newPrice);
            ps.setLong(2, foodId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long findQuantityByFoodId(long foodId) {
        String SQL = "SELECT quantity FROM stock " +
                "WHERE food_id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, foodId);
            ps.executeQuery();
            ResultSet res = ps.getResultSet();
            res.next();
            return res.getLong("quantity");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Stock findStockByFoodId(long foodId) {
        String SQL = "SELECT id, quantity, price FROM stock " +
                "WHERE food_id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, foodId);
            ps.executeQuery();
            ResultSet res = ps.getResultSet();
            res.next();
            return new Stock(
                    res.getLong("id"),
                    foodId,
                    res.getLong("quantity"),
                    res.getLong("price")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setQuantityOfFoodId(long foodId, long setQuantity) {
        String SQL = "UPDATE stock " +
                "SET quantity = ? " +
                "WHERE food_id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, setQuantity);
            ps.setLong(2, foodId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
