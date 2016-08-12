package com.example.repository;

import com.example.model.Book;
import com.example.model.BookResponse;
import com.example.model.BookSearch;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;


@Stateless
public class BookRepositoryStub implements BookRepository {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("abc");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    private EntityTransaction entityTransaction = entityManager.getTransaction();

    public BookResponse getAllBooks(int pageIndex, int offSet) {
        Query query = entityManager.createQuery("select b from Book b");
        int totalBooks=query.getResultList().size();
        BookResponse bookResponse=new BookResponse();
        bookResponse.setTotalCount(totalBooks);
        bookResponse.setBooks(query.setMaxResults(offSet).setFirstResult(pageIndex * offSet).getResultList());
        return bookResponse;
    }

    public Book findBookById(int id) {
        return entityManager.find(Book.class, id);
    }

    public Book updateBook(int bookId, Book book) {
        entityTransaction.begin();
        Book b = findBookById(bookId);
        b.setName(book.getName());
        b.setPublication(book.getPublication());
        b.setPrice(book.getPrice());
        entityTransaction.commit();
        return b;
    }

    public void deleteBook(int id) {
        Book book = entityManager.find(Book.class, id);
        if (book != null) {
            entityTransaction.begin();
            entityManager.remove(book);
            entityTransaction.commit();
        }
    }

    public Book createBook(Book book) {
        entityTransaction.begin();
        entityManager.persist(book);
        entityTransaction.commit();
        return book;
    }

    public List<Book> searchBookByName(String bookName) {
        Query query = entityManager.createQuery("select b from Book b where b.name like :name");
        query.setParameter("name", "%" + bookName + "%");
        return query.getResultList();
    }

    public List<Book> searchBookByObject(BookSearch bookSearch) {
        Query query = entityManager.createQuery("select b from Book b where b.price between:startPrice and :endPrice");
        query.setParameter("startPrice", bookSearch.getStartPrice());
        query.setParameter("endPrice", bookSearch.getEndPrice());
        return query.getResultList();
    }
}
