package com.katas.developmentbooks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookOrderTest {

    private BookOrderService bookOrderService;

    @BeforeEach
    void beforeEach() {
        bookOrderService = new BookOrderService();
    }

    @Test
    void buyNoBookTotalZero() {
        assertEquals(0d, bookOrderService.getTotalPrice());
    }

    @Test
    void buyOneBookTotal50() {
        bookOrderService.addBook(Book.CLEAN_CODE);
        assertEquals(50d, bookOrderService.getTotalPrice());
    }
}
