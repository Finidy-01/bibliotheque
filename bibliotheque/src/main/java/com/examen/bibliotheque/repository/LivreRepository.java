package com.examen.bibliotheque.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examen.bibliotheque.entity.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre,Long>, LivreRepositoryCustom {
    @Query(value = "SELECT TL.idLivre,TL.nb FROM topLivres TL ORDER BY TL.nb DESC ",nativeQuery = true)
    List<Object[]> getTopLivre();

    Livre findByIdLivre(Long idLivre);
    
    @Modifying
    @Query(value = "INSERT INTO LivreCategorie (idLivre, idCategorie) VALUES (:idLivre, :idCategorie)", nativeQuery = true)
    void insertLivreCategorie(int idLivre, int idCategorie);

    @Modifying
    @Query(value = "UPDATE Livre set idEditions = :idEditions , dateEdition = :dateEdition , isbn = :ISBN, numeroCote = :numeroCote, "
                    +"titre = :titre, auteur = :auteur, idCollections = :idCollection ,"
                    +"resumes = :resumes , theme = :theme , langue = :langue , nbPages = :nbPages, "
                    +"couverture = :couverture where idLivre = :idLivre",nativeQuery =true)
    void UpdateLivre(int idEditions,Date dateEdition,String ISBN,int numeroCote,
                    String titre,String auteur,int idCollection,String resumes,
                    String theme,String langue,int nbPages,String couverture, Long idLivre);

    @Modifying
    @Query(value = "DELETE FROM LivreCategorie l where l.idLivre = :idLivre", nativeQuery = true)
    void deleteCategorieLivre(int idLivre);
} 
