package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Entity
@Table(name = "utilisateurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private Enums.Role role;

    private String specialite;
    private Double tarifConsultation;

    @OneToMany(mappedBy = "generaliste")
    private List<Consultation> consultationsCrees;

    @OneToMany(mappedBy = "specialiste")
    private List<CreneauHoraire> creneauxDefinis;

    @OneToMany(mappedBy = "specialiste")
    private List<DemandeExpertise> expertisesRecues;
}