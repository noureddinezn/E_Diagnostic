package com.teleexpertise.dao;

import com.example.model.Enums;
import com.example.model.Utilisateur;
import com.teleexpertise.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UtilisateurDao {

    // ============ CREATE ============
    public void save(Utilisateur u) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(u);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Erreur lors de l'enregistrement", e);
        }
    }

    // ============ READ ============
    public Optional<Utilisateur> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(Utilisateur.class, id));
        }
    }

    public List<Utilisateur> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Utilisateur", Utilisateur.class)
                          .getResultList();
        }
    }

    public Optional<Utilisateur> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                        "FROM Utilisateur u WHERE u.email = :email", Utilisateur.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
        }
    }

    public List<Utilisateur> findByRole(Enums.Role role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                        "FROM Utilisateur u WHERE u.role = :role", Utilisateur.class)
                    .setParameter("role", role)
                    .getResultList();
        }
    }

    public List<Utilisateur> findSpecialistesBySpecialite(String specialite) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                        "FROM Utilisateur u WHERE u.role = :role AND u.specialite = :sp",
                        Utilisateur.class)
                    .setParameter("role", Enums.Role.SPECIALISTE)
                    .setParameter("sp", specialite)
                    .getResultList();
        }
    }

   
    public Utilisateur update(Utilisateur u) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Utilisateur merged = session.merge(u);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Erreur lors de la mise à jour", e);
        }
    }

  
    public void delete(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Utilisateur u = session.find(Utilisateur.class, id);
            if (u != null) {
                session.remove(u);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Erreur lors de la suppression", e);
        }
    }
}