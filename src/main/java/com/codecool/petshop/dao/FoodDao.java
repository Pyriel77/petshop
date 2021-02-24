package com.codecool.petshop.dao;

import com.codecool.petshop.model.Food;
import com.codecool.petshop.model.FoodType;

import java.util.List;

public interface FoodDao {
    List<Food> findFoodByType(FoodType foodType);
    List<Food> findFoodByBrand(String brandName);
}
