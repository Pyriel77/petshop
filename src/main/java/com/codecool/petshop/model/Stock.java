package com.codecool.petshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Stock {
    private final long id;
    private final long foodId;
    private final long quantity;
    private final long price;
}
