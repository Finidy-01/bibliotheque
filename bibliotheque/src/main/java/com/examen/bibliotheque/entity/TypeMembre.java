package com.examen.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TypeMembre")
public class TypeMembre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeMembre")
    private Long idTypeMembre;

    @Column(name = "nomTypeMembre")
    private String nomTypeMembre;

    @Column(name = "maxjouremprunt")
    private int maxjouremprunt;

    @Column(name = "sanction")
    private int sanction;
    

    public int getSanction() {
        return sanction;
    }

    public void setSanction(int sanction) {
        this.sanction = sanction;
    }

    public int getMaxjouremprunt() {
        return maxjouremprunt;
    }

    public void setMaxjouremprunt(int maxjouremprunt) {
        this.maxjouremprunt = maxjouremprunt;
    }

    public Long getIdTypeMembre() {
        return idTypeMembre;
    }

    public void setIdTypeMembre(Long idTypeMembre) {
        this.idTypeMembre = idTypeMembre;
    }

    public String getNomTypeMembre() {
        return nomTypeMembre;
    }

    public void setNomTypeMembre(String nomTypeMembre) {
        this.nomTypeMembre = nomTypeMembre;
    }

}
