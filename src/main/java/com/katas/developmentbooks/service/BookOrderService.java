package com.katas.developmentbooks.service;

import java.util.*;
import java.util.stream.Stream;

public class BookOrderService {

    private static final double SINGLE_BOOK_PRICE = 50d;

    private final List<Discount> discounts;

    private final List<Book> books = new ArrayList<>();

    public BookOrderService(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void addBook(Book book, int quantity) {
        for (int i = 0; i < quantity; i++)
            this.books.add(book);
    }

    public double getTotalPrice() {
        Stream<Discount> applicableDiscounts = getApplicableDiscounts();

        double bestDiscountAmount = applicableDiscounts.mapToDouble(this::getDiscountAmountByAmount).max().orElse(0);

        return getTotalPriceWithoutDiscount() - bestDiscountAmount;
    }

    private double getTotalPriceWithoutDiscount() {
        return SINGLE_BOOK_PRICE * books.size();
    }

    private double getDiscountAmountByAmount(Discount discount) {
        int numberOfSet = 0;

        int discountBooksNumber = discount.numberOfDifferentBooks();
        List<Book> clonedBooks = new ArrayList<>(books);
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

    private Stream<Discount> getApplicableDiscounts() {
        long numberOfDistinctBooks = getDistinctBooksCount();
        return discounts.stream().filter(discount -> numberOfDistinctBooks >= discount.numberOfDifferentBooks());
    }

    private long getDistinctBooksCount() {
        return books.stream().distinct().count();
    }
}
