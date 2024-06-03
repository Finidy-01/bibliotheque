package com.examen.bibliotheque.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examen.bibliotheque.entity.Emprunt;
import com.examen.bibliotheque.entity.TypeEmprunt;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt,Long>{

    @Modifying
    @Query("UPDATE Emprunt e set e.dateDelais = :date where e.idEmprunt = :idEmprunt")
    void validateEmprunt(Date date,int idEmprunt);

    @Modifying
    @Query("UPDATE Emprunt e set e.dateRendu = current_date where e.idEmprunt = :idEmprunt")
    void returnEmprunt(int idEmprunt);

    @Query("SELECT e FROM Emprunt e where e.dateDelais IS NULL")
    List<Emprunt> getNonValidateEmprunt();

    @Query("SELECT e FROM Emprunt e where e.dateRendu IS NULL AND e.dateDelais IS NOT NULL")
    List<Emprunt> getNonReturnedEmprunt();

    @Query("SELECT t FROM TypeEmprunt t")
    List<TypeEmprunt> getAllTypeEmprunt();
    
    Emprunt findByIdEmprunt(Long idEmprunt);

}