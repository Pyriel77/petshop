package com.codecool.petshop.model;

import java.sql.Date;

public class DogFood extends Food{
    public DogFood(long id, Date expirationDate, String brandName, String name) {
        super(id, FoodType.DOG, expirationDate, brandName, name);
    }
}
