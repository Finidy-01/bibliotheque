package com.examen.bibliotheque.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.bibliotheque.entity.Membre;
import com.examen.bibliotheque.entity.TypeMembre;
import com.examen.bibliotheque.repository.MembreRepository;
import com.examen.bibliotheque.repository.SanctionRepository;

@Service
public class MembreService {
    @Autowired
    MembreRepository membreRepository;

    @Autowired
    SanctionRepository sanctionRepository;

    public List<TypeMembre> getAllTypeMembre(){
        return membreRepository.getAllTypeMembre();
    };

    public void insertMembre (Membre membre){
        membreRepository.save(membre);
    }

    public Membre findMembreByIdenitfiant(String idMembre){
        return membreRepository.findByIdMembre(idMembre);
    }
    public boolean isSanctionned(Membre membre){
        Date date = sanctionRepository.getMaxDateSanction(membre.getIdMembre());
        LocalDate currentDate = LocalDate.now();
        Date now = Date.valueOf(currentDate);
        if(date == null){
            return false;
        }
        // System.out.println("DateACTUUUUUU : " + now);
        // System.out.println("Date de fin de sanction : " + date);
        return now.before(date);
    }

    public void save(Membre membre,int idTypeMembre){
        LocalDate currentDate = LocalDate.now();
        Date now = Date.valueOf(currentDate);
        membre.setDateInscription(now);
        membre.setTypeMembre(membreRepository.getTypeMembreById(idTypeMembre));
        membreRepository.save(membre);
    }

    public List<Membre> getAllMembre(){
        return membreRepository.getAllMembre();
    };

    public void updateMembre(Membre membre,int idTypeMembre){
        membre.setTypeMembre(membreRepository.getTypeMembreById(idTypeMembre));
        membreRepository.updateMembre(  membre.getTypeMembre().getIdTypeMembre(),
                                        membre.getNom(),
                                        membre.getDateNaissance(),
                                        membre.getAdresse(),
                                        membre.getIdMembre());
    }

    public String generate() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();

        // Génération des trois lettres aléatoires
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int letterIndex = random.nextInt(letters.length());
            randomString.append(letters.charAt(letterIndex));
        }

        // Génération des huit chiffres aléatoires
        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10); // nextInt(10) génère un nombre entre 0 et 9
            randomString.append(digit);
        }

        String generatedId = randomString.toString();;

        Membre membre = membreRepository.findByIdMembre(generatedId);
        while(membre != null)
        {
            generatedId = generate();
        }
        return generatedId;
    }
}
