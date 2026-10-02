package com.teleexpertise.dao;

import com.example.model.DemandeExpertise;
import com.example.model.Enums;

import java.util.List;
import java.util.Optional;

public class DemandeExpertiseDAO extends GenericDAO<DemandeExpertise> {

    public DemandeExpertiseDAO() {
        super(DemandeExpertise.class);
    }

    public List<DemandeExpertise> findBySpecialisteId(Long specialisteId) {
        return executeRead(session -> session.createQuery(
                        "FROM DemandeExpertise d WHERE d.specialiste.id = :id ORDER BY d.dateDemande DESC",
                        DemandeExpertise.class)
                .setParameter("id", specialisteId)
                .getResultList());
    }

    public Optional<DemandeExpertise> findByConsultationId(Long consultationId) {
        return executeRead(session -> session.createQuery(
                        "FROM DemandeExpertise d WHERE d.consultation.id = :id",
                        DemandeExpertise.class)
                .setParameter("id", consultationId)
                .uniqueResultOptional());
    }

    /** Demandes d'un spécialiste filtrées par priorité (URGENTE, NORMALE...). */
    public List<DemandeExpertise> findBySpecialisteAndPriorite(Long specialisteId, Enums.Priorite priorite) {
        return executeRead(session -> session.createQuery(
                        "FROM DemandeExpertise d WHERE d.specialiste.id = :id AND d.priorite = :p "
                                + "ORDER BY d.dateDemande DESC",
                        DemandeExpertise.class)
                .setParameter("id", specialisteId)
                .setParameter("p", priorite)
                .getResultList());
    }
}