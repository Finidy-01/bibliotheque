package com.examen.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Exemplaire")
public class Exemplaire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExemplaire")
    private Long idExemplaire;

    @ManyToOne
    @JoinColumn(name = "idLivre")
    private Livre idLivre;

    @Transient
    private String disponible;


    public String getDisponible() {
        return disponible;
    }


    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }


    public Long getIdExemplaire() {
        return idExemplaire;
    }


    public void setIdExemplaire(Long idExemplaire) {
        this.idExemplaire = idExemplaire;
    }


    public Livre getIdLivre() {
        return idLivre;
    }


    public void setIdLivre(Livre idLivre) {
        this.idLivre = idLivre;
    }

}
