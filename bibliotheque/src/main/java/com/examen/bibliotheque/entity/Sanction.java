package com.examen.bibliotheque.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="Sanction")
public class Sanction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idSanction")
    private Long idSanction;

    @OneToOne
    @JoinColumn(name="idMembre")
    private Membre membre;

    @Column(name="dateDebut")
    private Date dateDebut;

    @Column(name="dateFin")
    private Date dateFin;

    public Long getIdSanction() {
        return idSanction;
    }

    public void setIdSanction(Long idSanction) {
        this.idSanction = idSanction;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

}
