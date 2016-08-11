package com.example.repository;


import com.example.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks(int max, int offSet);

    Book findBookById(int id);

    Book updateBook(int bookId, Book book);

    void deleteBook(int id);

    Book createBook(Book book);
}
