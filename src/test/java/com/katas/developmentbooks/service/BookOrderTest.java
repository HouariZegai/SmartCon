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

    @Test
    void buySameBookTwiceTotal100EUR() {
        bookOrderService.addBook(Book.CLEAN_CODE);
        bookOrderService.addBook(Book.CLEAN_CODE);
        assertEquals(100d, bookOrderService.getTotalPrice());
    }

    @Test
    void buyTwoDifferentBooksThenGet5PercentDiscount() {
        bookOrderService.addBook(Book.CLEAN_CODE);
        bookOrderService.addBook(Book.CLEAN_ARCHITECTURE);
        assertEquals(95d, bookOrderService.getTotalPrice());
    }

    @Test
    void buyThreeBooksWithTwoDifferentBooksThenGet5PercentDiscountForTheTwoDifferentBooksOnly() {
        bookOrderService.addBook(Book.CLEAN_CODE);
        bookOrderService.addBook(Book.CLEAN_CODE);
        bookOrderService.addBook(Book.CLEAN_ARCHITECTURE);
        assertEquals(145d, bookOrderService.getTotalPrice());
    }
}
