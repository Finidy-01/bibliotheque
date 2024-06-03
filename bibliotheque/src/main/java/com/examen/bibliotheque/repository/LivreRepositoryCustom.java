package com.examen.bibliotheque.repository;

import java.util.List;

public interface LivreRepositoryCustom  {
    List<Object[]> searchLivre(String sql);
}
