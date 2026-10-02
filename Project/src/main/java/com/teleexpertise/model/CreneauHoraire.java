package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "creneaux_horaires")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreneauHoraire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;

    @Enumerated(EnumType.STRING)
    private Enums.StatutCreneau statut;

    @ManyToOne
    @JoinColumn(name = "specialiste_id")
    private Utilisateur specialiste;

    @OneToOne(mappedBy = "creneauHoraire")
    private DemandeExpertise demandeExpertise;
}