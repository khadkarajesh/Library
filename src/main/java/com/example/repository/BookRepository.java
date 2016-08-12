package com.example.repository;


import com.example.model.Book;
import com.example.model.BookResponse;
import com.example.model.BookSearch;

import java.util.List;

public interface BookRepository {
    BookResponse getAllBooks(int max, int offSet);

    Book findBookById(int id);

    Book updateBook(int bookId, Book book);

    void deleteBook(int id);

    Book createBook(Book book);

    List<Book> searchBookByName(String bookName);

    List<Book> searchBookByObject(BookSearch bookSearch);
}
