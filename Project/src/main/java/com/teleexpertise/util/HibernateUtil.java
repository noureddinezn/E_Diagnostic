package com.teleexpertise.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.example.model.*;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = build();

    private static SessionFactory build() {
        return new Configuration()
                .configure()   
                .addAnnotatedClass(Utilisateur.class)
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Consultation.class)
                .addAnnotatedClass(DemandeExpertise.class)
                .addAnnotatedClass(CreneauHoraire.class)
                .addAnnotatedClass(Ordonnance.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}