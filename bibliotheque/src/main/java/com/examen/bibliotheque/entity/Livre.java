package com.examen.bibliotheque.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLivre")
    private Long idLivre;

    @ManyToOne
    @JoinColumn(name ="idEditions")
    private Editions edition;

    @Column(name = "dateEdition")
    private Date dateEdition;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "numeroCote")
    private int numeroCote;

    @Column(name = "titre")
    private String titre;

    @Column(name = "auteur")
    private String auteur;

    @ManyToOne
    @JoinColumn(name ="idCollections")
    private Collections collection;

    @Column(name = "resumes")
    private String resumes;

    @OneToMany
    @JoinTable(
        name = "LivreCategorie",
        joinColumns = @JoinColumn(name = "idLivre"),
        inverseJoinColumns = @JoinColumn(name = "idCategorie")
    )
    private List<Categorie> categories;

    @Column(name = "theme")
    private String theme;

    @Column(name = "langue")
    private String langue;

    @Column(name = "nbPages")
    private int nbPages;

    @Column(name = "couverture")
    private String couverture;

    @OneToMany(mappedBy = "idLivre")
    private List<Exemplaire> Exemplaires;

    @Transient
    private int nombreEmprunt;

    public int getNombreEmprunt() {
        return nombreEmprunt;
    }

    public void setNombreEmprunt(int nombreEmprunt) {
        this.nombreEmprunt = nombreEmprunt;
    }

    public Long getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Long idLivre) {
        this.idLivre = idLivre;
    }

    public Editions getEdition() {
        return edition;
    }

    public void setEdition(Editions edition) {
        this.edition = edition;
    }

    public Date getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public int getNumeroCote() {
        return numeroCote;
    }

    public void setNumeroCote(int numeroCote) {
        this.numeroCote = numeroCote;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Collections getCollection() {
        return collection;
    }

    public void setCollection(Collections collection) {
        this.collection = collection;
    }

    public String getResumes() {
        return resumes;
    }

    public void setResumes(String resumes) {
        this.resumes = resumes;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    public List<Exemplaire> getExemplaires() {
        return Exemplaires;
    }

    public void setExemplaires(List<Exemplaire> exemplaires) {
        Exemplaires = exemplaires;
    }


}
