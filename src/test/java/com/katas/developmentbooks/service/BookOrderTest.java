package com.katas.developmentbooks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
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
    void buyNoBookTotal0EUR() {
        assertEquals(0d, bookOrderService.getTotalPrice());
    }

    @ParameterizedTest
    @EnumSource(Book.class)
    void buyOneBookTotal50EUR(Book book) {
        bookOrderService.addBook(book);
        assertEquals(50d, bookOrderService.getTotalPrice());
    }
}
