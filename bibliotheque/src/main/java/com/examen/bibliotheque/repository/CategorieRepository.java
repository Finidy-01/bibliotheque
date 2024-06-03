package com.examen.bibliotheque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examen.bibliotheque.entity.Categorie;

@Repository
public interface CategorieRepository  extends JpaRepository<Categorie,Long> {
    @Query("SELECT c FROM Categorie c ORDER BY c.nomCategorie asc")
    List<Categorie> getAllCategorie();   

    Categorie findByNomCategorie(String nomCategorie);

    Categorie findByIdCategorie(Long idCategorie);
}
