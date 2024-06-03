package com.examen.bibliotheque.entity;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "Emprunt")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmprunt")
    private Long idEmprunt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idExemplaire")
    private Exemplaire Exemplaires;

    @Column(name = "dateEmprunt")
    private Date dateEmprunt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMembre")
    private Membre membre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTypeEmprunt")
    private TypeEmprunt typeEmprunt;

    @Column(name = "DateDelais")
    private Date dateDelais;

    @Column(name = "DateRendu")
    private Date dateRendu;

    public Long getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Long idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Exemplaire getExemplaires() {
        return Exemplaires;
    }

    public void setExemplaires(Exemplaire exemplaires) {
        Exemplaires = exemplaires;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateEmprunt(String dateEmprunt)throws Exception {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateUtil = inputFormat.parse(dateEmprunt);
		
		DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = outputFormat.format(dateUtil);
		
		java.sql.Date dateSql = java.sql.Date.valueOf(formattedDate);
		
        this.dateEmprunt = dateSql;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public TypeEmprunt getTypeEmprunt() {
        return typeEmprunt;
    }

    public void setTypeEmprunt(TypeEmprunt typeEmprunt) {
        this.typeEmprunt = typeEmprunt;
    }

    public Date getDateDelais() {
        return dateDelais;
    }

    public void setDateDelais(Date dateDelais) {
        this.dateDelais = dateDelais;
    }

    public void setDateDelais(String dateDelais)throws Exception {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateUtil = inputFormat.parse(dateDelais);
		
		DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = outputFormat.format(dateUtil);
		
		java.sql.Date dateSql = java.sql.Date.valueOf(formattedDate);
		
        this.dateDelais = dateSql;
    }

    public Date getDateRendu() {
        return dateRendu;
    }

    public void setDateRendu(Date dateRendu) {
        this.dateRendu = dateRendu;
    }

    public void setDateRendu(String dateRendu)throws Exception {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateUtil = inputFormat.parse(dateRendu);
		
		DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = outputFormat.format(dateUtil);
		
		java.sql.Date dateSql = java.sql.Date.valueOf(formattedDate);
		
        this.dateRendu = dateSql;
    }

}
