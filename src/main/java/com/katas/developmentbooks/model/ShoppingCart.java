package com.katas.developmentbooks.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    List<Book> books = new ArrayList<>();

    public ShoppingCart addBook(Book book) {
        this.books.add(book);
        return this;
    }

    public ShoppingCart addBook(Book book, int quantity) {
        for (int i = 0; i < quantity; i++)
            this.books.add(book);

        return this;
    }

    public List<Book> getBooks() {
        return books;
    }
}
