package com.examen.bibliotheque.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.examen.bibliotheque.entity.TypeEmprunt;


@Repository
public interface TypeEmpruntRepository extends JpaRepository<TypeEmprunt,Long>{
    TypeEmprunt findByIdTypeEmprunt(Long idTypeEmprunt);
}
