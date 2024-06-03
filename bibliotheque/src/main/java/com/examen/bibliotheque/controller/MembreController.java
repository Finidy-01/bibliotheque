package com.examen.bibliotheque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.examen.bibliotheque.entity.Membre;
import com.examen.bibliotheque.entity.TypeMembre;
import com.examen.bibliotheque.service.MembreService;

import jakarta.servlet.http.HttpSession;


@Controller
public class MembreController {

    @Autowired
    MembreService membreService;
    
    @GetMapping("/membre/check")
    public String index(String idMembre,HttpSession session) {
        Membre membre = membreService.findMembreByIdenitfiant(idMembre);
        if(membre==null){
            return "redirect:/";            
        }
        session.setAttribute("membre", membre);
        return "redirect:/home";
    }

    @GetMapping("/membre/form")
    public String MembreForm(Model model) {
        List<TypeMembre>  typeMembre = membreService.getAllTypeMembre();
        Membre membre = new Membre();
        String idMembre = membreService.generate();
        membre.setIdMembre(idMembre);
        model.addAttribute("listTypeMembre", typeMembre);
        model.addAttribute("membre",membre );
        return"membre_form";
    }

    @GetMapping("/membre/insert")
    public String membre_insert(@ModelAttribute Membre membre,Model model,@Param("idTypeMembre")int idTypeMembre) {
        membreService.save(membre,idTypeMembre);
        return"redirect:/membres";
    }

    @GetMapping("/membres")
    public String membreList(Model model) {
        List<Membre>  lisMembre = membreService.getAllMembre();
        model.addAttribute("lisMembre", lisMembre);
        return "membre_list";
    }

    @GetMapping("/membre/{idMembre}/update_form")
    public String membreUpdateForm(Model model,@PathVariable String idMembre) {
        Membre membre = membreService.findMembreByIdenitfiant(idMembre);
        List<TypeMembre>  typeMembre = membreService.getAllTypeMembre();
        model.addAttribute("listTypeMembre", typeMembre);
        model.addAttribute("membre", membre);
        return "membre_update";
    }    
    
    @GetMapping("/membre/update")
    public String membreUpdate(Model model,@ModelAttribute Membre membre,@Param("idTypeMembre")int idTypeMembre) {
        membreService.updateMembre(membre,idTypeMembre);
        return "redirect:/membres";
    }

}
