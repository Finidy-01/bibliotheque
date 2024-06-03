package com.examen.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Collections")
public class Collections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCollections")
    private Long idCollections;

    @Column(name = "nomCollections")
    private String nomCollections;

    public Long getIdCollections() {
        return idCollections;
    }

    public void setIdCollections(Long idCollections) {
        this.idCollections = idCollections;
    }

    public String getNomCollections() {
        return nomCollections;
    }

    public void setNomCollections(String nomCollections) {
        this.nomCollections = nomCollections;
    }   
}
