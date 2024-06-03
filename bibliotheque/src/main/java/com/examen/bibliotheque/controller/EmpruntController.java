package com.examen.bibliotheque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.examen.bibliotheque.entity.Membre;
import com.examen.bibliotheque.service.EmpruntService;

import jakarta.servlet.http.HttpSession;


@Controller
public class EmpruntController {
    @Autowired
    EmpruntService empruntService;   
    
    @GetMapping("emprunt/insert")
    public String insertEmprunt(int idExemplaire,int idTypeEmprunt,Model model,HttpSession session) {
        Membre membre =(Membre) session.getAttribute("membre");
        empruntService.insertEmprunt(membre.getIdMembre(), idExemplaire,idTypeEmprunt);
        return "redirect:/home";
    }

    @GetMapping("/admin/emprunt/{idEmprunt}/validate")
    public String validateEmprunt(@PathVariable int idEmprunt) {
        empruntService.validerEmprunt( idEmprunt);
        return "redirect:/admin";
    }

    @GetMapping("/admin/emprunt/{idEmprunt}/rendre")
    public String rendreEmprunt(@PathVariable int idEmprunt,HttpSession session) {
        empruntService.returnEmprunt(idEmprunt);
        return "redirect:/admin/emprunt/retour";
    }
    
}
