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
        bookOrderService.addBook(Book.CLEAN_CODE, 2);
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
        bookOrderService.addBook(Book.CLEAN_CODE, 2);
        bookOrderService.addBook(Book.CLEAN_ARCHITECTURE);
        assertEquals(145d, bookOrderService.getTotalPrice());
    }

    @Test
    void buyThreeDifferentBooksThenGet10PercentDiscount() {
        bookOrderService.addBook(Book.CLEAN_CODE);
        bookOrderService.addBook(Book.CLEAN_ARCHITECTURE);
        bookOrderService.addBook(Book.TEST_DRIVEN_DEVELOPMENT);
        assertEquals(135d, bookOrderService.getTotalPrice());
    }

    @Test
    void buyFourBookWithThreeDifferentBooksThenGet10PercentDiscountForTheThreeDifferentBooksOnly() {
        bookOrderService.addBook(Book.CLEAN_CODE);
        bookOrderService.addBook(Book.CLEAN_ARCHITECTURE, 2);
        bookOrderService.addBook(Book.TEST_DRIVEN_DEVELOPMENT);
        assertEquals(185d, bookOrderService.getTotalPrice());
    }

    @Test
    void buyFourDifferentBooksThenGet20PercentDiscount() {
        bookOrderService.addBook(Book.CLEAN_CODE);
        bookOrderService.addBook(Book.CLEAN_ARCHITECTURE);
        bookOrderService.addBook(Book.TEST_DRIVEN_DEVELOPMENT);
        bookOrderService.addBook(Book.WORKING_EFFECTIVELY_WITH_LEGACY_CODE);
        assertEquals(160d, bookOrderService.getTotalPrice());
    }

    @Test
    void buyFiveBookWithFourDifferentBooksThenGet20PercentDiscountForTheFourDifferentBooksOnly() {
        bookOrderService.addBook(Book.CLEAN_CODE);
        bookOrderService.addBook(Book.CLEAN_CODER);
        bookOrderService.addBook(Book.CLEAN_ARCHITECTURE);
        bookOrderService.addBook(Book.TEST_DRIVEN_DEVELOPMENT, 2);
        assertEquals(210d, bookOrderService.getTotalPrice());
    }

    @Test
    void buyFiveDifferentBooksThenGet25PercentDiscountForTheFiveDifferentBooks() {
        bookOrderService.addBook(Book.CLEAN_CODE);
        bookOrderService.addBook(Book.CLEAN_CODER);
        bookOrderService.addBook(Book.CLEAN_ARCHITECTURE);
        bookOrderService.addBook(Book.TEST_DRIVEN_DEVELOPMENT);
        bookOrderService.addBook(Book.WORKING_EFFECTIVELY_WITH_LEGACY_CODE);
        assertEquals(187.5, bookOrderService.getTotalPrice());
    }

    @Test
    void buySixBooksWithFiveDifferentBooksThenGet25PercentDiscountForTheFiveDifferentBooksOnly() {
        bookOrderService.addBook(Book.CLEAN_CODE);
        bookOrderService.addBook(Book.CLEAN_CODER);
        bookOrderService.addBook(Book.CLEAN_ARCHITECTURE);
        bookOrderService.addBook(Book.TEST_DRIVEN_DEVELOPMENT);
        bookOrderService.addBook(Book.WORKING_EFFECTIVELY_WITH_LEGACY_CODE, 2);
        assertEquals(237.5, bookOrderService.getTotalPrice());
    }
}
