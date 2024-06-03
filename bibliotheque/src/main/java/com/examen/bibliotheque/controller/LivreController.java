package com.examen.bibliotheque.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.examen.bibliotheque.entity.Categorie;
import com.examen.bibliotheque.entity.Collections;
import com.examen.bibliotheque.entity.Editions;
import com.examen.bibliotheque.entity.Exemplaire;
import com.examen.bibliotheque.entity.Livre;
import com.examen.bibliotheque.entity.TypeEmprunt;
import com.examen.bibliotheque.service.EmpruntService;
import com.examen.bibliotheque.service.LivreService;

@Controller
public class LivreController {

    @Autowired
    LivreService livreService;

    @Autowired
    EmpruntService empruntService;   
    
    @GetMapping("livre/{idLivre}/exemplaires")
    public String livreExemplaires(@PathVariable int idLivre,Model model) {
        List<Exemplaire> listExemplaire = livreService.getAllExemplaireWithDisponibiliteByLivre(idLivre);
        List<TypeEmprunt> listTypeEmprunt = empruntService.getAllTypeEmprunt();
        model.addAttribute("listExemplaire", listExemplaire);
        model.addAttribute("listTypeEmprunt", listTypeEmprunt);
        return "livre_exemplaires";
    }

    @GetMapping("/livres/search")
    public String livreSearch(@RequestParam Map<String, Object> allParams,Model model) {
        try {
            List<Livre> listLivres = livreService.searchLivre(allParams);
            System.out.println("LENNNNNNNNGTH"+listLivres.size());
            System.out.println("AUTEUUUUUUUUUR"+listLivres.get(0).getAuteur());
            List<Livre> topLivres = livreService.getTopLivre();
            List<Categorie> listCategorie = livreService.getAllCategorie(); 
            model.addAttribute("topLivres", topLivres);
            model.addAttribute("listLivres", listLivres);
            model.addAttribute("listCategorie", listCategorie);
            return "home";   
        } catch (Exception e) {
           e.printStackTrace();
        }
        return"redirect:/home";
    }

    @GetMapping("/livre/form")
    public String MembreForm(Model model) {
        List<Categorie>  listCategories = livreService.getAllCategorie();
        List<Editions>  listEditions = livreService.getAllEditions();
        List<Collections>  listCollections = livreService.getAllCollections();
    
        model.addAttribute("livre", new Livre());
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("listEditions", listEditions);
        model.addAttribute("listCollections", listCollections);

        return"livre_form";
    }

    @GetMapping("/livre/insert")
    public String insert(@ModelAttribute Livre livre,
                        @Param(value = "idCategories")int[] idCategories,@Param(value = "idCollections")int idCollections,
                        @Param(value = "idEditions")int idEditions, 
                        @Param(value = "nbExemplaire")int nbExemplaire) {
        livreService.insertLivre(livre, idCategories, idCollections, idEditions,nbExemplaire);
        return"redirect:/livres";
    }

    @GetMapping("/livres")
    public String livreList(Model model) {
        List<Livre>  listLivre = livreService.getAllLivre();
        model.addAttribute("listLivre", listLivre);
        return"livre_list";
    }

    @GetMapping("/livre/{idLivre}/update_form")
    public String livreUpdateForm(Model model,
                                    @PathVariable int idLivre) {

        Livre livre  = livreService.findByIdLivre(((Number) idLivre).longValue());
        List<Categorie>  listCategories = livreService.getAllCategorie();
        List<Editions>  listEditions = livreService.getAllEditions();
        List<Collections>  listCollections = livreService.getAllCollections();

        model.addAttribute("livre", livre);
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("listEditions", listEditions);
        model.addAttribute("listCollections", listCollections);
        return"livre_update";
    }
    
    @GetMapping("/livre/update")
    public String updateLvre(Model model,@ModelAttribute Livre livre,
                            @Param(value = "idCategories")int[] idCategories,
                            @Param(value = "idCollections")int idCollections,
                            @Param(value = "idEditions")int idEditions ) {
        livreService.update(livre, idCategories, idCollections, idEditions);
        return"redirect:/livres";
    }

}
