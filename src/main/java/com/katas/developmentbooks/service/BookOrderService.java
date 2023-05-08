package com.katas.developmentbooks.service;

import java.util.ArrayList;
import java.util.List;

public class BookOrderService {

    private static final double SINGLE_BOOK_PRICE = 50d;

    private final List<Book> books = new ArrayList<>();

    public double getTotalPrice() {
        return books.size() * SINGLE_BOOK_PRICE;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
