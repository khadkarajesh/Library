package com.example.repositary;

import com.example.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by rajesh on 8/4/16.
 */
public class AuthorRepositoryStub implements AuthorRepository {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("abc");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private EntityTransaction entityTransaction = entityManager.getTransaction();

    public Author createAuthor(Author author) {
        entityTransaction.begin();
        entityManager.persist(author);
        entityTransaction.commit();
        return author;
    }
}
