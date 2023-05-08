package com.katas.developmentbooks.service;

import java.util.ArrayList;
import java.util.List;

public class BookOrderService {

    private static final double SINGLE_BOOK_PRICE = 50d;

    private final List<Book> books = new ArrayList<>();

    public double getTotalPrice() {
        long numberOfDistinctBooks = books.stream().distinct().count();

        if(numberOfDistinctBooks == 3) {
            double discount = (SINGLE_BOOK_PRICE * 3) / 100 * 10;
            return (books.size() * SINGLE_BOOK_PRICE) - discount;
        }

        if(numberOfDistinctBooks == 2) {
            double discount = (SINGLE_BOOK_PRICE * 2) / 100 * 5;
            return (books.size() * SINGLE_BOOK_PRICE) - discount;
        }

        return books.size() * SINGLE_BOOK_PRICE;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
