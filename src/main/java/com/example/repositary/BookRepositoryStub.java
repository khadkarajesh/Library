package com.example.repositary;

import com.example.model.Book;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;


@Stateless
public class BookRepositoryStub implements BookRepository {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("abc");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();


    /*@PersistenceContext(unitName = "abc")
    EntityManager entityManager;*/
    private EntityTransaction entityTransaction = entityManager.getTransaction();

    public List<Book> getAllBooks() {
        return null;
    }

    public Book findBookById(int id) {
        return entityManager.find(Book.class, id);
    }

    public Book updateBook(Book book) {
        entityTransaction.begin();
        Book book1 = entityManager.merge(book);
        entityTransaction.commit();
        return book1;
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
}
