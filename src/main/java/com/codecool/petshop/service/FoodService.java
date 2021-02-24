package com.codecool.petshop.service;

import com.codecool.petshop.dao.FoodDao;
import com.codecool.petshop.dao.StockDao;
import com.codecool.petshop.model.Food;
import com.codecool.petshop.model.FoodType;
import com.codecool.petshop.view.UserInterface;

import java.util.List;
import java.util.stream.Collectors;

public class FoodService {
    private final UserInterface ui;
    private final FoodDao foodDao;
    private final StockDao stockDao;

    public FoodService(UserInterface ui, FoodDao foodDao, StockDao stockDao) {
        this.ui = ui;
        this.foodDao = foodDao;
        this.stockDao = stockDao;
    }

    public List<Food> findFoodByBrand(String brandName) {
        List<Food> foodList = foodDao.findFoodByBrand(brandName);
        return foodList.stream().filter(Food::isNotExpired).collect(Collectors.toList());
    }

    public List<Food> findFoodByType(FoodType foodType) {
        List<Food> foodList = foodDao.findFoodByType(foodType);
        return foodList.stream().filter(Food::isNotExpired).collect(Collectors.toList());
    }

    protected void run() {
        boolean running = true;

        while (running) {
            ui.printTitle("Customer menu");
            ui.printOption('b', "List by Brand");
            ui.printOption('t', "List by Type");
            ui.printOption('p', "Purchase by food id");
            ui.printOption('q', "Quit");
            char choice = ui.choice("btpq");

            switch (choice) {
                case 'b':
                    String brandName = ui.readString("Brand? ", "brand 1");
                    findFoodByBrand(brandName).forEach(ui::println);
                    break;
                case 't':
                    String type = ui.readString("Type? ", "CAT");
                    findFoodByType(FoodType.getEnum(type)).forEach(ui::println);
                    break;
                case 'p':
                    long foodId = ui.readInt("Food id?", 1);
                    long currentQuantity = stockDao.findQuantityByFoodId(foodId);
                    if (currentQuantity > 0) {
                        stockDao.setQuantityOfFoodId(foodId,  currentQuantity - 1);
                    } else {
                        ui.println("Product out of stock!");
                    }
                    break;
                case 'q':
                    running = false;
                    break;
            }
        }
    }
}
