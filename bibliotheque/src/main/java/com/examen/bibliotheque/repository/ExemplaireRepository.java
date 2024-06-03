package com.examen.bibliotheque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examen.bibliotheque.entity.Exemplaire;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire,Long>{
    @Query(value = "SELECT ED.idExemplaire, ED.disponibilite FROM ExemplaireDispo ED  where ED.idLivre = :idLivre ORDER BY ED.disponibilite",
            nativeQuery = true)
    List<Object[]> getAllExemplaireWithDisponibiliteByLivre(int idLivre);

    Exemplaire findByIdExemplaire(Long idExemplaire);
}
