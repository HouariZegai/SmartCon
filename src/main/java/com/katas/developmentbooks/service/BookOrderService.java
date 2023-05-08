package com.katas.developmentbooks.service;

import java.util.ArrayList;
import java.util.List;

public class BookOrderService {

    private static final double SINGLE_BOOK_PRICE = 50d;

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        this.books.add(book);
    }

    public double getTotalPrice() {
        double discount = getDiscountAmount();

        return (books.size() * SINGLE_BOOK_PRICE) - discount;
    }

    private double getDiscountAmount() {
        long numberOfDistinctBooks = getDistinctBooksCount();

        if(numberOfDistinctBooks == 5) {
            return (SINGLE_BOOK_PRICE * 5) * 0.25;
        }
        if(numberOfDistinctBooks == 4) {
            return (SINGLE_BOOK_PRICE * 4) * 0.20;
        }
        if(numberOfDistinctBooks == 3) {
            return (SINGLE_BOOK_PRICE * 3) * 0.10;
        }
        if (numberOfDistinctBooks == 2) {
            return (SINGLE_BOOK_PRICE * 2) * 0.05;
        }

        return 0;
    }

    private long getDistinctBooksCount() {
        return books.stream().distinct().count();
    }
}
