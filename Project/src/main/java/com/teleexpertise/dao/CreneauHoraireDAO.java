package com.teleexpertise.dao;

import com.example.model.CreneauHoraire;
import com.example.model.Enums;

import java.time.LocalDateTime;
import java.util.List;

public class CreneauHoraireDAO extends GenericDAO<CreneauHoraire> {

    public CreneauHoraireDAO() {
        super(CreneauHoraire.class);
    }

    public List<CreneauHoraire> findBySpecialisteId(Long specialisteId) {
        return executeRead(session -> session.createQuery(
                        "FROM CreneauHoraire c WHERE c.specialiste.id = :id ORDER BY c.dateHeureDebut",
                        CreneauHoraire.class)
                .setParameter("id", specialisteId)
                .getResultList());
    }

    public List<CreneauHoraire> findDisponiblesBySpecialiste(Long specialisteId) {
        return executeRead(session -> session.createQuery(
                        "FROM CreneauHoraire c WHERE c.specialiste.id = :id "
                                + "AND c.statut = :statut AND c.dateHeureDebut > :now "
                                + "ORDER BY c.dateHeureDebut",
                        CreneauHoraire.class)
                .setParameter("id", specialisteId)
                .setParameter("statut", Enums.StatutCreneau.DISPONIBLE)
                .setParameter("now", LocalDateTime.now())
                .getResultList());
    }
}