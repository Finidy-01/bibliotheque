package com.examen.bibliotheque.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examen.bibliotheque.entity.Membre;
import com.examen.bibliotheque.entity.TypeMembre;

@Repository
public interface MembreRepository extends JpaRepository<Membre,String>{
    @Query("SELECT t FROM TypeMembre t")
    List<TypeMembre> getAllTypeMembre();

    @Query("SELECT t FROM TypeMembre t where t.idTypeMembre = :idTypeMembre")
    TypeMembre getTypeMembreById(int idTypeMembre);

    @Query("SELECT m FROM Membre m")
    List<Membre> getAllMembre();

    @Modifying
    @Query("Update Membre m set m.typeMembre = :typeMembre, m.nom = :nom"
                                +", m.dateNaissance = :dateNaissance"
                                +", m.adresse = :adresse"
                                +" where m.idMembre = :idMembre ")
    void updateMembre(Long typeMembre,String nom,Date dateNaissance,String adresse,String idMembre);

    Membre findByIdMembre(String idMembre);

}
