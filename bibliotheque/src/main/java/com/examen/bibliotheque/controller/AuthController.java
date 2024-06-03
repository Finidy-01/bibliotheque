package com.examen.bibliotheque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.examen.bibliotheque.entity.Categorie;
import com.examen.bibliotheque.entity.Emprunt;
import com.examen.bibliotheque.entity.Livre;
import com.examen.bibliotheque.service.EmpruntService;
import com.examen.bibliotheque.service.LivreService;
import com.examen.bibliotheque.service.MembreService;


@Controller
public class AuthController {

    @Autowired
    LivreService livreService;

    @Autowired
    EmpruntService empruntService;

    @Autowired
    MembreService membreService;

    @GetMapping("/home")
    public String homePage(Model model) {
        List<Livre> topLivres = livreService.getTopLivre();
        List<Livre> listLivres = livreService.getAllLivre();
        List<Categorie> listCategorie = livreService.getAllCategorie(); 
        model.addAttribute("topLivres", topLivres);
        model.addAttribute("listLivres", listLivres);
        model.addAttribute("listCategorie", listCategorie);
        System.out.println("CATEGGGGGGGG"+listCategorie.get(0).getNomCategorie());
        return "home";
    }

    @GetMapping("/")
    public String AuthPage(Model model) {
        return "Auth_page";
    }

    @GetMapping("/admin")
    public String AdminPage(Model model) {
        List<Emprunt> listeEmprunt = empruntService.getNonValidateEmprunt();
        model.addAttribute("listEmprunt", listeEmprunt);
        return "admin";
    }

    @GetMapping("/admin/emprunt/retour")
    public String RetourEmpruntPage(Model model) {
        List<Emprunt> listeEmprunt = empruntService.getNonReturnedEmprunt();
        model.addAttribute("listEmprunt", listeEmprunt);
        return "/admin_retour";
    }

}
