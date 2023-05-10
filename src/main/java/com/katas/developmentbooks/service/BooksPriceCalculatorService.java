package com.katas.developmentbooks.service;

import com.katas.developmentbooks.model.ShoppingCart;

public interface BooksPriceCalculatorService {

    double calculateTotal(ShoppingCart shoppingCart);
}
