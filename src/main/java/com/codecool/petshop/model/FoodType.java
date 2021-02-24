package com.codecool.petshop.model;

public enum FoodType {
    DOG, CAT, FISH;

    private static FoodType[] VALUES = FoodType.values();

    public static FoodType getEnum(String value) {
        for(FoodType foodType : VALUES)
            if(foodType.toString().equalsIgnoreCase(value)) return foodType;
        throw new IllegalArgumentException();
    }
}
