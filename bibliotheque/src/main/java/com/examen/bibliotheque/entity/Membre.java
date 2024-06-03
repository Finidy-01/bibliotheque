package com.examen.bibliotheque.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Membre")
public class Membre {
    @Id
    @Column(name = "idMembre")
    private String idMembre;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="typeMembre")
    private TypeMembre typeMembre;

    @Column(name="nom")
    private String nom;

    @Column(name="dateNaissance")
    private Date dateNaissance;

    @Column(name="adresse")
    private String adresse;

    @Column(name="dateInscription")
    private Date dateInscription;

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public TypeMembre getTypeMembre() {
        return typeMembre;
    }

    public void setTypeMembre(TypeMembre typeMembre) {
        this.typeMembre = typeMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

}
