package com.teleexpertise.dao;

import com.example.model.Patient;

import java.util.List;
import java.util.Optional;

public class PatientDao extends GenericDAO<Patient> {

    public PatientDao() {
        super(Patient.class);
    }

    public Optional<Patient> findByNumSecuriteSociale(String num) {
        return executeRead(session -> session.createQuery(
                        "FROM Patient p WHERE p.numSecuriteSociale = :num", Patient.class)
                .setParameter("num", num)
                .uniqueResultOptional());
    }

    /** Recherche par nom (insensible à la casse, contient). */
    public List<Patient> searchByNom(String nom) {
        return executeRead(session -> session.createQuery(
                        "FROM Patient p WHERE lower(p.nom) LIKE :nom ORDER BY p.nom",
                        Patient.class)
                .setParameter("nom", "%" + nom.toLowerCase() + "%")
                .getResultList());
    }
}