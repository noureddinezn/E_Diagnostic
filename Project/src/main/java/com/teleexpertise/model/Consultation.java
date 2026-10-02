package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreation;
    private String tensionArterielle;
    private Integer frequenceCardiaque;
    private Double temperature;
    private Integer frequenceRespiratoire;
    private Double poids;
    private Double taille;
    private String symptomes;
    private String examenClinique;
    private String diagnostic;

    @Enumerated(EnumType.STRING)
    private Enums.StatutConsultation statut;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur generaliste;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL)
    private DemandeExpertise demandeExpertise;

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL)
    private Ordonnance ordonnance;
}