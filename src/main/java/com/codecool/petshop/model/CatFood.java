package com.codecool.petshop.model;

import java.sql.Date;

public class CatFood extends Food{
    public CatFood(long id, Date expirationDate, String brandName, String name) {
        super(id, FoodType.CAT, expirationDate, brandName, name);
    }
}
