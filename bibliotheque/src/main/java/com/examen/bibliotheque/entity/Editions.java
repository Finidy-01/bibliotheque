package com.examen.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Editions")
public class Editions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEditions")
    private Long idEditions;

    @Column(name = "nomEditions")
    private String nomEditions;

    public Long getIdEditions() {
        return idEditions;
    }

    public void setIdEditions(Long idEditions) {
        this.idEditions = idEditions;
    }

    public String getNomEditions() {
        return nomEditions;
    }

    public void setNomEditions(String nomEditions) {
        this.nomEditions = nomEditions;
    }   
}
