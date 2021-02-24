package com.codecool.petshop.model;

import java.sql.Date;

public class FishFood extends Food {
    public FishFood(long id, Date expirationDate, String brandName, String name) {
        super(id, FoodType.FISH, expirationDate, brandName, name);
    }
}
