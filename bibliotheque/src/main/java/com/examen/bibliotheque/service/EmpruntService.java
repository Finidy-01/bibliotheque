package com.examen.bibliotheque.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.bibliotheque.entity.Emprunt;
import com.examen.bibliotheque.entity.Exemplaire;
import com.examen.bibliotheque.entity.Membre;
import com.examen.bibliotheque.entity.Sanction;
import com.examen.bibliotheque.entity.TypeEmprunt;
import com.examen.bibliotheque.repository.EmpruntRepository;
import com.examen.bibliotheque.repository.SanctionRepository;
import com.examen.bibliotheque.repository.TypeEmpruntRepository;

import jakarta.transaction.Transactional;

@Service
public class EmpruntService {
    @Autowired
    EmpruntRepository empruntRepository;   

    @Autowired
    TypeEmpruntRepository typeEmpruntRepository;

    @Autowired
    SanctionRepository sanctionRepository;

    @Transactional
    public void validerEmprunt(int idEmprunt){
        Emprunt emprunt = empruntRepository.findByIdEmprunt(((Number) idEmprunt).longValue());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(emprunt.getDateEmprunt());
        calendar.add(Calendar.DAY_OF_MONTH, emprunt.getMembre().getTypeMembre().getMaxjouremprunt());
        emprunt.setDateDelais(new Date(calendar.getTimeInMillis()));
        empruntRepository.validateEmprunt(emprunt.getDateDelais(),emprunt.getIdEmprunt().intValue());
    }

    @Transactional
    public void returnEmprunt(int idEmprunt){
        empruntRepository.returnEmprunt(idEmprunt);
        Emprunt emprunt = empruntRepository.findByIdEmprunt(((Number) idEmprunt).longValue());
        LocalDate now = LocalDate.now();
        Date currentDate = Date.valueOf(now);
        if(emprunt.getDateDelais().before(emprunt.getDateRendu())){
            java.time.LocalDate localDateDelais = emprunt.getDateDelais().toLocalDate();
            java.time.LocalDate localDateFin = emprunt.getDateRendu().toLocalDate();
            int dayDiff = (int) ChronoUnit.DAYS.between(localDateDelais, localDateFin);
            int nbJours = dayDiff * emprunt.getMembre().getTypeMembre().getSanction();
            System.out.println("DATEDELAIIIIIIIS"+emprunt.getDateDelais());
            System.out.println("DATERENDUUUUU"+emprunt.getDateRendu());
            System.out.println("NBBBBBBBBBBB"+nbJours);
            Sanction sanction = new Sanction();
            Date dateFinLastSanction = sanctionRepository.getMaxDateSanction(emprunt.getMembre().getIdMembre());
            
            if(dateFinLastSanction != null && dateFinLastSanction.after(currentDate)){
                sanction.setDateDebut(dateFinLastSanction);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateFinLastSanction);
                calendar.add(Calendar.DAY_OF_MONTH,nbJours);
                sanction.setDateFin(new Date(calendar.getTimeInMillis()));
                sanction.setMembre(emprunt.getMembre());
            }else{
                sanction.setDateDebut(currentDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                calendar.add(Calendar.DAY_OF_MONTH,nbJours);
                sanction.setDateFin(new Date(calendar.getTimeInMillis()));
                sanction.setMembre(emprunt.getMembre());
            }
            sanctionRepository.save(sanction);
        }
    }

    public List<Emprunt> getNonReturnedEmprunt(){
        return empruntRepository.getNonReturnedEmprunt();
    };

    public List<Emprunt> getNonValidateEmprunt(){
        return empruntRepository.getNonValidateEmprunt();
    };

    public void insertEmprunt(String idMembre,int idExemplaire,int idTypeEmprunt){
        Emprunt emprunt = new Emprunt();
        Membre membre = new Membre();
        membre.setIdMembre(idMembre);
        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setIdExemplaire(((Number) idExemplaire).longValue());
        TypeEmprunt typeEmprunt = typeEmpruntRepository.findByIdTypeEmprunt(((Number) idTypeEmprunt).longValue());
        emprunt.setMembre(membre);
        emprunt.setExemplaires(exemplaire);
        emprunt.setTypeEmprunt(typeEmprunt);
        LocalDate curreDate = LocalDate.now();
        emprunt.setDateEmprunt(Date.valueOf(curreDate));
        empruntRepository.save(emprunt);
    }

    public List<TypeEmprunt> getAllTypeEmprunt(){
        return empruntRepository.getAllTypeEmprunt();
    }

}
