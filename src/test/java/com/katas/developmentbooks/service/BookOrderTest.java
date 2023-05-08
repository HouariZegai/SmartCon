package com.katas.developmentbooks.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookOrderTest {

    @Test
    void buyNoBookTotalZero() {
        BookOrderService bookOrderService = new BookOrderService();
        assertEquals(0d, bookOrderService.getTotalPrice());
    }
}
