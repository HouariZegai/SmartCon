package com.katas.developmentbooks.service;

import com.katas.developmentbooks.model.ShoppingCart;

public interface BookPriceCalculatorService {

    double calculateTotal(ShoppingCart shoppingCart);
}
