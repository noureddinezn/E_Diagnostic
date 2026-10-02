package com.teleexpertise.dao;

import com.teleexpertise.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public abstract class GenericDAO<T> {

    private final Class<T> entityClass;

    protected GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    
    public void save(T entity) {
        executeInTransaction(session -> {
            session.persist(entity);
            return null;
        });
    }

    public Optional<T> findById(Long id) {
        return executeRead(session -> Optional.ofNullable(session.find(entityClass, id)));
    }

    public List<T> findAll() {
        return executeRead(session ->
                session.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
                       .getResultList());
    }

    public T update(T entity) {
        return executeInTransaction(session -> session.merge(entity));
    }


    public void delete(Long id) {
        executeInTransaction(session -> {
            T entity = session.find(entityClass, id);
            if (entity != null) {
                session.remove(entity);
            }
            return null;
        });
    }


    protected <R> R executeInTransaction(Function<Session, R> action) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            R result = action.apply(session);
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new RuntimeException("Erreur DAO (" + entityClass.getSimpleName() + ")", e);
        }
    }

    protected <R> R executeRead(Function<Session, R> action) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return action.apply(session);
        }
    }
}