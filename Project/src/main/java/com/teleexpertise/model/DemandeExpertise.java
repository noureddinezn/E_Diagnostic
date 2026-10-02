package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "demandes_expertise")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @Enumerated(EnumType.STRING)
    private Enums.Priorite priorite;

    @Enumerated(EnumType.STRING)
    private Enums.TypeExpertise typeExpertise;

    private LocalDateTime dateDemande;
    private String avisSpecialiste;
    private LocalDateTime dateReponse;

    @OneToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "specialiste_id")
    private Utilisateur specialiste;

    @OneToOne
    @JoinColumn(name = "creneau_id")
    private CreneauHoraire creneauHoraire;
}