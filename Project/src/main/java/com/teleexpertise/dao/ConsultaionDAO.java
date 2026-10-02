package com.teleexpertise.dao;

import com.example.model.Consultation;
import com.example.model.Enums;

import java.util.List;

public class ConsultaionDAO extends GenericDAO<Consultation> {

    public ConsultaionDAO() {
        super(Consultation.class);
    }

    public List<Consultation> findByStatut(Enums.StatutConsultation statut) {
        return executeRead(session -> session.createQuery(
                        "FROM Consultation c WHERE c.statut = :statut ORDER BY c.dateCreation DESC",
                        Consultation.class)
                .setParameter("statut", statut)
                .getResultList());
    }

    public List<Consultation> findByGeneralisteId(Long generalisteId) {
        return executeRead(session -> session.createQuery(
                        "FROM Consultation c WHERE c.generaliste.id = :id ORDER BY c.dateCreation DESC",
                        Consultation.class)
                .setParameter("id", generalisteId)
                .getResultList());
    }

    public List<Consultation> findByPatientId(Long patientId) {
        return executeRead(session -> session.createQuery(
                        "FROM Consultation c WHERE c.patient.id = :id ORDER BY c.dateCreation DESC",
                        Consultation.class)
                .setParameter("id", patientId)
                .getResultList());
    }
}