package com.examen.bibliotheque.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.bibliotheque.entity.Categorie;
import com.examen.bibliotheque.entity.Collections;
import com.examen.bibliotheque.entity.Editions;
import com.examen.bibliotheque.entity.Exemplaire;
import com.examen.bibliotheque.entity.Livre;
import com.examen.bibliotheque.repository.CategorieRepository;
import com.examen.bibliotheque.repository.CollectionsRepository;
import com.examen.bibliotheque.repository.EditionsRepository;
import com.examen.bibliotheque.repository.ExemplaireRepository;
import com.examen.bibliotheque.repository.LivreRepository;

import jakarta.persistence.Column;
import jakarta.transaction.Transactional;

@Service
public class LivreService {
    @Autowired
    LivreRepository livreRepository;

    @Autowired
    CollectionsRepository collectionsRepository;

    @Autowired
    EditionsRepository editionsRepository;

    @Autowired
    CategorieRepository categorieRepository;

    @Autowired
    ExemplaireRepository exemplaireRepository;

    @Transactional
    public void insertLivre(Livre livre,int[] idCategories ,int idCollections,int idEdition,int nbExemplaire){
        livre.setCollection(collectionsRepository.findByIdCollections(((Number)idCollections).longValue()));
        livre.setEdition(editionsRepository.findByIdEditions(((Number)idEdition).longValue()));
        Livre livreinserted = livreRepository.save(livre);
        for (int id : idCategories) {
            System.out.println("IDLLLLLIIIIVRE :"+livreinserted.getIdLivre()+" , IDCATEG : "+id);
            livreRepository.insertLivreCategorie((livreinserted.getIdLivre().intValue()),id);
        }
        for (int i = 0; i < nbExemplaire; i++) {
            Exemplaire exemplaire = new Exemplaire();
            exemplaire.setIdLivre(livreinserted);
            exemplaireRepository.save(exemplaire);
        }
    }

    public List<Collections> getAllCollections(){
        return collectionsRepository.getAllCollections();
    };

    public List<Editions> getAllEditions(){
        return editionsRepository.getAllEditions();
    };

    public List<Categorie> getAllCategorie(){
        return categorieRepository.getAllCategorie();
    };

    public  List<Livre> getAllLivre(){
        Iterable<Livre> iterable = livreRepository.findAll();
        List<Livre> livreList = new ArrayList<Livre>();
        iterable.forEach(livre -> livreList.add(livre));
        return livreList;
    }

    public List<Livre> getTopLivre(){
        List<Object[]> livres = livreRepository.getTopLivre();
        List<Livre> topLivres = new ArrayList<>();
        for (Object[] result : livres) {
            Livre newLivre = livreRepository.findByIdLivre(((Number) result[0]).longValue());
            newLivre.setNombreEmprunt(((Number) result[1]).intValue());
            topLivres.add(newLivre);
        }
        return topLivres;
    };

    public List<Exemplaire> getAllExemplaireWithDisponibiliteByLivre(int idLivre){
        List<Object[]> exemplaires = exemplaireRepository.getAllExemplaireWithDisponibiliteByLivre(idLivre);
        List<Exemplaire> listExemplaires = new ArrayList<>();
        for (Object[] result : exemplaires) {
            Exemplaire exemplaire = exemplaireRepository.findByIdExemplaire(((Number) result[0]).longValue());
            exemplaire.setDisponible(String.valueOf(result[1]));
            listExemplaires.add(exemplaire);
        }
        return listExemplaires;
    };

    public List<Livre> searchLivre(Map<String, Object> allParams)throws Exception{
        String sql = "";
        boolean notchanged  = true;
        List<Livre> listLivres = new ArrayList<>();

        for (Map.Entry<String, Object> entry : allParams.entrySet()) {
            String paramName = entry.getKey();
            Object paramValue = entry.getValue();

            if (paramValue != null && !paramValue.toString().isEmpty()) {
                Field field = getFieldAnnotatedWithColumnName(Livre.class, paramName);
                Class<?> fieldType = null;
                if(field != null){
                    fieldType = field.getType();
                }
                if (notchanged) {
                    if(fieldType == String.class && fieldType != null ){
                        sql += "WHERE UPPER(" + paramName + ") = UPPER(LIKE %'" + paramValue + "'%) ";
                    }else{
                        sql += "WHERE " + paramName + " = '" + paramValue + "' ";
                    }
                    notchanged = false;
                } else {
                    if(fieldType == String.class && fieldType != null ){
                        sql += "AND UPPER(" + paramName + ") = UPPER(LIKE %'" + paramValue + "'%) ";
                    }else{
                        sql += "AND " + paramName + " = '" + paramValue + "' ";
                    }
                }
            }
        }
        System.out.println("CUSTOMMMMMM SQLLLLLLL "+sql);
        List<Object[]> idLivres = livreRepository.searchLivre(sql);
        for (Object[] objects : idLivres) {
            Livre livre = livreRepository.findByIdLivre(((Number) objects[0]).longValue());
            listLivres.add(livre);
            System.out.println("LIIIIIIIIIVRE"+livre.getAuteur());
        }
        return listLivres;
    }

    public Field getFieldAnnotatedWithColumnName(Class<?> clazz, String columnName) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (column.name().equals(columnName)) {
                    return field;
                }
            }
        }
        return null;
    }

    public Livre findByIdLivre(Long idLivre){
        return livreRepository.findByIdLivre(idLivre);
    };

    @Transactional
    public void update(Livre livre,int[] idCategories ,int idCollections,int idEdition){
        livreRepository.UpdateLivre(idEdition, livre.getDateEdition(),
                                    livre.getISBN(), livre.getNumeroCote(), livre.getTitre(),
                                     livre.getAuteur(), idCollections, livre.getResumes(), 
                                     livre.getTheme(), livre.getLangue(), livre.getNbPages(), livre.getCouverture(),livre.getIdLivre());
        
        livreRepository.deleteCategorieLivre(livre.getIdLivre().intValue());
        for (int id : idCategories) {
            System.out.println("UPDAAAAAAAATE :"+livre.getIdLivre()+" , IDCATEG : "+id);
            livreRepository.insertLivreCategorie((livre.getIdLivre().intValue()),id);
        }
    }

   
}
