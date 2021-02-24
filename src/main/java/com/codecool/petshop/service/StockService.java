package com.codecool.petshop.service;

import com.codecool.petshop.dao.StockDao;
import com.codecool.petshop.model.Stock;
import com.codecool.petshop.view.UserInterface;

public class StockService {
    private UserInterface ui;
    private StockDao stockDao;

    public StockService(UserInterface ui, StockDao stockDao) {
        this.ui = ui;
        this.stockDao = stockDao;
    }

    protected void run() {
        boolean running = true;

        while (running) {
            ui.printTitle("Admin menu");
            ui.printOption('p', "Set price by food id");
            ui.printOption('u', "Add to quantity by food id");
            ui.printOption('f', "Find stock information by food id");
            ui.printOption('q', "Quit");
            char choice = ui.choice("pufq");

            long foodId;
            switch (choice) {
                case 'p':
                    foodId = ui.readInt("Food id?", 1);
                    long newPrice = ui.readInt("New price?", 99);
                    stockDao.setPrice(foodId, newPrice);
                    break;
                case 'u':
                    foodId = ui.readInt("Food id?", 1);
                    long quantity = ui.readInt("Add how many?", 1);
                    stockDao.setQuantityOfFoodId(foodId, stockDao.findQuantityByFoodId(foodId) + quantity);
                    break;
                case 'f':
                    foodId = ui.readInt("Food id?", 1);
                    Stock stock = stockDao.findStockByFoodId(foodId);
                    ui.println(stock);
                    break;
                case 'q':
                    running = false;
                    break;
            }
        }
    }

    void setPrice(int foodId, int newPrice) {
        stockDao.setPrice(foodId, newPrice);
    }

    void setQuantity(int foodId, int addQuantity) {
        stockDao.setQuantityOfFoodId(foodId, addQuantity);
    }

    Stock findStockByFoodId(long foodId) {
        return stockDao.findStockByFoodId(foodId);
    }
}
