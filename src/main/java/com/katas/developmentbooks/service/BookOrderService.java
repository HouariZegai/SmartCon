package com.katas.developmentbooks.service;

public class BookOrderService {

    private static final double SINGLE_BOOK_PRICE = 50d;

    private Book book;

    public double getTotalPrice() {
        if(book != null)
            return SINGLE_BOOK_PRICE;

        return 0d;
    }

    public void addBook(Book book) {
        this.book = book;
    }
}
