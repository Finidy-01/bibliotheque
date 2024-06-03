package com.examen.bibliotheque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examen.bibliotheque.entity.Collections;

@Repository
public interface CollectionsRepository  extends JpaRepository<Collections,Long>{
    @Query("SELECT c FROM Collections c ORDER BY c.nomCollections asc")
    List<Collections> getAllCollections();

    Collections findByIdCollections(Long idCollections);
    Collections findByNomCollections(String nomCollections);
}
