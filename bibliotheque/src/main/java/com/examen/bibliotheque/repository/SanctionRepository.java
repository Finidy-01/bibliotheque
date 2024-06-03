package com.examen.bibliotheque.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examen.bibliotheque.entity.Sanction;

@Repository
public interface SanctionRepository extends JpaRepository<Sanction,Long> {
    @Query(value="SELECT MAX(datefin) from sanction where idMembre = :idMembre",nativeQuery = true)
    Date getMaxDateSanction(String idMembre);
}
