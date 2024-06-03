package com.examen.bibliotheque.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="LivreCategorie")
public class LivreCategorie {
    @Id 
    @Column(name = "idLivre")
    private Long idLivre;

    @Id 
    @Column(name = "idCategorie")
    private Long idCategorie;

    public Long getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Long idLivre) {
        this.idLivre = idLivre;
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }
}
