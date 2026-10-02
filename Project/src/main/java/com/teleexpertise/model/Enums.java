package com.example.model;

public class Enums {
    public enum Role {
        INFIRMIER, GENERALISTE, SPECIALISTE
    }

    public enum StatutConsultation {
        EN_FILE_ATTENTE, EN_COURS, EN_ATTENTE_AVIS_SPECIALISTE, TERMINEE
    }

    public enum Priorite {
        URGENTE, NORMALE, NON_URGENTE
    }

    public enum TypeExpertise {
        SYNCHRONE, ASYNCHRONE
    }

    public enum StatutCreneau {
        DISPONIBLE, RESERVE, PASSE
    }
}