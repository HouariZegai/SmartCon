package com.katas.developmentbooks.service;

import com.katas.developmentbooks.model.Book;
import com.katas.developmentbooks.model.Discount;

import java.util.*;
import java.util.stream.Stream;

public class BookPriceCalculator {

    private static final double SINGLE_BOOK_PRICE = 50d;

    private final List<Discount> discounts;

    public BookPriceCalculator(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public double calculateTotal(ShoppingCart shoppingCart) {
        Stream<Discount> applicableDiscounts = getApplicableDiscounts(shoppingCart);

        double bestDiscountAmount = applicableDiscounts
                .mapToDouble(discount -> getDiscountAmountByAmountAndShoppingCart(discount, shoppingCart))
                .max().orElse(0);

        return getTotalPriceWithoutDiscount(shoppingCart) - bestDiscountAmount;
    }

    private double getTotalPriceWithoutDiscount(ShoppingCart shoppingCart) {
        return SINGLE_BOOK_PRICE * shoppingCart.getBooks().size();
    }

    private double getDiscountAmountByAmountAndShoppingCart(Discount discount, ShoppingCart shoppingCart) {
        int numberOfSet = 0;

        int discountBooksNumber = discount.numberOfDifferentBooks();
        List<Book> clonedBooks = new ArrayList<>(shoppingCart.getBooks());
        boolean newSetFound;
        do {
            newSetFound = false;
            Set<Book> theFoundedSet = new HashSet<>(discountBooksNumber);

            for (Book book : clonedBooks) {
                theFoundedSet.add(book);
                if (theFoundedSet.size() == discountBooksNumber) {
                    newSetFound = true;
                    numberOfSet += 1;
                    break;
                }
            }
            theFoundedSet.forEach(clonedBooks::remove);

        } while (newSetFound);

        return (SINGLE_BOOK_PRICE * discountBooksNumber) * discount.percentage() * numberOfSet;
    }

    private Stream<Discount> getApplicableDiscounts(ShoppingCart shoppingCart) {
        long numberOfDistinctBooks = getDistinctBooksCount(shoppingCart);
        return discounts.stream().filter(discount -> numberOfDistinctBooks >= discount.numberOfDifferentBooks());
    }

    private long getDistinctBooksCount(ShoppingCart shoppingCart) {
        return shoppingCart.getBooks().stream().distinct().count();
    }
}
