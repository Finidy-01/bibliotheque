package com.examen.bibliotheque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examen.bibliotheque.entity.Editions;

@Repository
public interface EditionsRepository  extends JpaRepository<Editions,Long>{
    @Query("SELECT e FROM Editions e ORDER BY e.nomEditions asc")
    List<Editions> getAllEditions();  
    
    Editions findByIdEditions(Long idEditions);
    Editions findByNomEditions(String nomEditions);
}
