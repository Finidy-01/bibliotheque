package com.examen.bibliotheque.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class LivreRepositoryCustomImpl implements LivreRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> searchLivre(String sql) {
        Query query = entityManager.createNativeQuery("SELECT idLivre FROM LivreComplet " + sql +" group by idLivre ");
        return query.getResultList();
    }
}