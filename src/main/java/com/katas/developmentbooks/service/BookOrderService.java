package com.katas.developmentbooks.service;

import java.util.*;

public class BookOrderService {

    private static final double SINGLE_BOOK_PRICE = 50d;

    private final List<Book> books = new ArrayList<>();

    private final List<Discount> discounts;

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
        double discount = getDiscountAmount();

        return (books.size() * SINGLE_BOOK_PRICE) - discount;
    }

    private double getDiscountAmount() {
        long numberOfDistinctBooks = getDistinctBooksCount();

        return discounts.stream()
                .filter(discount -> discount.numberOfDifferentBooks() <= numberOfDistinctBooks)
                .findFirst()
                .map(discount -> (SINGLE_BOOK_PRICE * discount.numberOfDifferentBooks()) * discount.percentage())
                .orElse(0d);
    }

    private long getDistinctBooksCount() {
        return books.stream().distinct().count();
    }
}
