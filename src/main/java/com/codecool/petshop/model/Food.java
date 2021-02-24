package com.codecool.petshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public abstract class Food {
    private final long id;
    private final FoodType foodType;
    private final Date expirationDate;
    private final String brandName;
    private final String name;

    public boolean isNotExpired() {
        LocalDate now = LocalDate.now();
        return now.isBefore(LocalDate.from(expirationDate.toLocalDate()));
    }

    public static Food createFood(long id, Date expirationDate, String brandName, String name, FoodType foodType) {
        switch (foodType) {
            case CAT:
                return new CatFood(id, expirationDate, brandName, name);
            case DOG:
                return new DogFood(id, expirationDate, brandName, name);
            case FISH:
                return new FishFood(id, expirationDate, brandName, name);
        }
        return null;
    }
}
