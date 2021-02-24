package com.codecool.petshop.service;

import com.codecool.petshop.dao.FoodDao;
import com.codecool.petshop.dao.StockDao;
import com.codecool.petshop.model.*;
import com.codecool.petshop.view.UserInterface;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FoodServiceTest {
    FoodDao foodDaoMock;
    StockDao stockDaoMock;
    FoodService foodService;
    List<Food> foodList;
    List<Food> emptyList;
    List<Food> notExpiredList;
    List<Food> dogFoodList;

    @BeforeEach
    void init() {
        foodDaoMock = mock(FoodDao.class);
        stockDaoMock = mock(StockDao.class);
        foodService = new FoodService(new UserInterface(System.in, System.out), foodDaoMock, stockDaoMock);
        CatFood catFood = new CatFood(1, Date.valueOf(LocalDate.now()), "brand 1", "cat food 1");
        CatFood catFood2 = new CatFood(4, Date.valueOf(LocalDate.of(2022, 1, 1)), "brand 1", "cat food 2");
        DogFood dogFood = new DogFood(2, Date.valueOf(LocalDate.of(2022, 2, 1)), "brand 2", "dog food 1");
        FishFood fishFood = new FishFood(3, Date.valueOf(LocalDate.of(2022, 3, 1)), "brand 2", "fish food 1");
        FishFood fishFood2 = new FishFood(5, Date.valueOf(LocalDate.now()), "brand 3", "fish food 2");
        foodList = List.of(catFood, catFood2, dogFood, fishFood, fishFood2);
        emptyList = new ArrayList<>();
        notExpiredList = List.of(catFood2, dogFood, fishFood);
        dogFoodList = List.of(dogFood);
    }

    @Order(1)
    @Test
    void findFoodByType_askForDogFood_get1() {
        when(foodDaoMock.findFoodByType(FoodType.DOG)).thenReturn(dogFoodList);
        assertEquals(dogFoodList, foodService.findFoodByType(FoodType.DOG));
    }

    @Order(2)
    @Test
    void findFoodByBrand_askForBrandWithNonExpiredProducts_matchesNonExpiredList() {
        List<Food> allBrand1 = foodList.stream()
                .filter(food -> food.getBrandName().equals("brand 1"))
                .collect(Collectors.toList());
        List<Food> notExpiredBrand1 = notExpiredList.stream()
                .filter(food -> food.getBrandName().equals("brand 1"))
                .collect(Collectors.toList());

        when(foodDaoMock.findFoodByBrand(notNull())).thenReturn(allBrand1);
        assertEquals(notExpiredBrand1, foodService.findFoodByBrand("brand 1"));
    }

    @Order(3)
    @Test
    void findFoodByBrand_askForBrandThatIsAllExpired_emptyList() {
        List<Food> allBrand3 = foodList.stream()
                .filter(food -> food.getBrandName().equals("brand 3"))
                .collect(Collectors.toList());

        when(foodDaoMock.findFoodByBrand(notNull())).thenReturn(allBrand3);
        assertEquals(emptyList, foodService.findFoodByBrand("brand 3"));
    }
}