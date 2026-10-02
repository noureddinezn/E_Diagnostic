package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String numSecuriteSociale;
    private String mutuelle;
    private String antecedents;
    private String allergies;
    private String traitementsEnCours;

    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;
}