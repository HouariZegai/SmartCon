package com.katas.developmentbooks.service;

import com.katas.developmentbooks.model.Book;
import com.katas.developmentbooks.model.Discount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookOrderTest {

    private BookOrderService bookOrderService;

    private final List<Discount> discounts = Arrays.asList(
            new Discount(5, 0.25),
            new Discount(4, 0.20),
            new Discount(3, 0.10),
            new Discount(2, 0.05)
    );

    @BeforeEach
    void beforeEach() {
        bookOrderService = new BookOrderService(discounts);
    }

    @Test
    void buyNoBookTotal0EUR() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertEquals(0d, bookOrderService.getTotalPrice(shoppingCart));
    }

    @ParameterizedTest
    @EnumSource(Book.class)
    void buyOneBookTotal50EUR(Book book) {
        ShoppingCart shoppingCart = new ShoppingCart().addBook(book);

        assertEquals(50d, bookOrderService.getTotalPrice(shoppingCart));
    }

    @Test
    void buySameBookTwiceTotal100EUR() {
        ShoppingCart shoppingCart = new ShoppingCart().addBook(Book.CLEAN_CODE, 2);

        assertEquals(100d, bookOrderService.getTotalPrice(shoppingCart));
    }

    @Test
    void buyTwoDifferentBooksThenGet5PercentDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart()
                .addBook(Book.CLEAN_CODE)
                .addBook(Book.CLEAN_ARCHITECTURE);

        assertEquals(95d, bookOrderService.getTotalPrice(shoppingCart));
    }

    @Test
    void buyThreeBooksWithTwoDifferentBooksThenGet5PercentDiscountForTheTwoDifferentBooksOnly() {
        ShoppingCart shoppingCart = new ShoppingCart()
                .addBook(Book.CLEAN_CODE, 2)
                .addBook(Book.CLEAN_ARCHITECTURE);

        assertEquals(145d, bookOrderService.getTotalPrice(shoppingCart));
    }

    @Test
    void buyThreeDifferentBooksThenGet10PercentDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart().addBook(Book.CLEAN_CODE)
                .addBook(Book.CLEAN_ARCHITECTURE)
                .addBook(Book.TEST_DRIVEN_DEVELOPMENT);

        assertEquals(135d, bookOrderService.getTotalPrice(shoppingCart));
    }

    @Test
    void buyFourBookWithThreeDifferentBooksThenGet10PercentDiscountForTheThreeDifferentBooksOnly() {
        ShoppingCart shoppingCart = new ShoppingCart().addBook(Book.CLEAN_CODE)
                .addBook(Book.CLEAN_ARCHITECTURE, 2)
                .addBook(Book.TEST_DRIVEN_DEVELOPMENT);

        assertEquals(185d, bookOrderService.getTotalPrice(shoppingCart));
    }

    @Test
    void buyFourDifferentBooksThenGet20PercentDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart().addBook(Book.CLEAN_CODE)
                .addBook(Book.CLEAN_ARCHITECTURE)
                .addBook(Book.TEST_DRIVEN_DEVELOPMENT)
                .addBook(Book.WORKING_EFFECTIVELY_WITH_LEGACY_CODE);

        assertEquals(160d, bookOrderService.getTotalPrice(shoppingCart));
    }

    @Test
    void buyFiveBookWithFourDifferentBooksThenGet20PercentDiscountForTheFourDifferentBooksOnly() {
        ShoppingCart shoppingCart = new ShoppingCart().addBook(Book.CLEAN_CODE)
                .addBook(Book.CLEAN_CODER)
                .addBook(Book.CLEAN_ARCHITECTURE)
                .addBook(Book.TEST_DRIVEN_DEVELOPMENT, 2);

        assertEquals(210d, bookOrderService.getTotalPrice(shoppingCart));
    }

    @Test
    void buyFiveDifferentBooksThenGet25PercentDiscountForTheFiveDifferentBooks() {
        ShoppingCart shoppingCart = new ShoppingCart().addBook(Book.CLEAN_CODE)
                .addBook(Book.CLEAN_CODER)
                .addBook(Book.CLEAN_ARCHITECTURE)
                .addBook(Book.TEST_DRIVEN_DEVELOPMENT)
                .addBook(Book.WORKING_EFFECTIVELY_WITH_LEGACY_CODE);

        assertEquals(187.5, bookOrderService.getTotalPrice(shoppingCart));
    }

    @Test
    void buySixBooksWithFiveDifferentBooksThenGet25PercentDiscountForTheFiveDifferentBooksOnly() {
        ShoppingCart shoppingCart = new ShoppingCart().addBook(Book.CLEAN_CODE)
                .addBook(Book.CLEAN_CODER)
                .addBook(Book.CLEAN_ARCHITECTURE)
                .addBook(Book.TEST_DRIVEN_DEVELOPMENT)
                .addBook(Book.WORKING_EFFECTIVELY_WITH_LEGACY_CODE, 2);

        assertEquals(237.5, bookOrderService.getTotalPrice(shoppingCart));
    }

    @Test
    void buyEightBooksWithTwoDiscountPossibilitiesThenGetBestPrice() {
        ShoppingCart shoppingCart = new ShoppingCart().addBook(Book.CLEAN_CODE, 2)
                .addBook(Book.CLEAN_CODER, 2)
                .addBook(Book.CLEAN_ARCHITECTURE, 2)
                .addBook(Book.TEST_DRIVEN_DEVELOPMENT)
                .addBook(Book.WORKING_EFFECTIVELY_WITH_LEGACY_CODE);

        assertEquals(320d, bookOrderService.getTotalPrice(shoppingCart));
    }
}
