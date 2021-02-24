package com.codecool.petshop.dao;

import com.codecool.petshop.model.Stock;

public interface StockDao {
    void setPrice(long foodId, long newPrice);
    long findQuantityByFoodId(long foodId);
    Stock findStockByFoodId(long foodId);
    void setQuantityOfFoodId(long foodId, long setQuantity);
}
