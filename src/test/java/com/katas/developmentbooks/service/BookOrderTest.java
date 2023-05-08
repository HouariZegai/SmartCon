package com.katas.developmentbooks.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookOrderTest {

    @Test
    void buyNoBookTotalZero() {
        BookOrderService bookOrderService = new BookOrderService();
    }
}
