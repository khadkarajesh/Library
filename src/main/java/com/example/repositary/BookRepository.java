package com.example.repositary;


import com.example.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();

    Book findBookById(int id);

    Book updateBook(Book book);

    void deleteBook(int id);

    Book createBook(Book book);
}
