package com.examen.bibliotheque.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "TypeEmprunt")
public class TypeEmprunt {
    @Id
    @Column(name = "idTypeEmprunt")
    private Long idTypeEmprunt;

    @Column(name = "nomTypeEmprunt")
    private String nomTypeEmprunt;

    public Long getIdTypeEmprunt() {
        return idTypeEmprunt;
    }

    public void setIdTypeEmprunt(Long idTypeEmprunt) {
        this.idTypeEmprunt = idTypeEmprunt;
    }

    public String getNomTypeEmprunt() {
        return nomTypeEmprunt;
    }

    public void setNomTypeEmprunt(String nomTypeEmprunt) {
        this.nomTypeEmprunt = nomTypeEmprunt;
    }
}
