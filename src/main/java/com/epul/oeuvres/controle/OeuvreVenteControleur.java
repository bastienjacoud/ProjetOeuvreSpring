package com.epul.oeuvres.controle;


import com.epul.oeuvres.dao.OeuvreVenteService;
import com.epul.oeuvres.dao.ProprietaireService;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Oeuvrevente;
import com.epul.oeuvres.metier.Proprietaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Les méthode du contrôleur répondent à des sollicitations
// des pages JSP

@Controller
public class OeuvreVenteControleur {

    private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

    @RequestMapping(value = "ajouterOeuvre.htm")
    public ModelAndView AjouteOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        ProprietaireService proprietaireService = new ProprietaireService();
        try {
            request.setAttribute("mesProprietaires", proprietaireService.consulterListeProp());
            destinationPage = "ajouterOeuvre";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insererOeuvre.htm")
    public ModelAndView insererOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage;
        try {
            OeuvreVenteService oeuvreVenteService = new OeuvreVenteService();
            ProprietaireService proprietaireService = new ProprietaireService();
            Oeuvrevente oeuvrevente = new Oeuvrevente();
            oeuvrevente.setTitreOeuvrevente(request.getParameter("titre"));
            oeuvrevente.setEtatOeuvrevente("L");
            oeuvrevente.setPrixOeuvrevente(Integer.valueOf(request.getParameter("prix")));
            oeuvrevente.setProprietaire(proprietaireService.rechercherProprietaire(Integer.valueOf(request.getParameter("idProprietaire"))));
            oeuvreVenteService.insertOeuvreVente(oeuvrevente);
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        destinationPage = "home";
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "listerOeuvre.htm")
    public ModelAndView listerOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            OeuvreVenteService oeuvreVenteService = new OeuvreVenteService();
            request.setAttribute("mesOeuvresV", oeuvreVenteService.consulterListeOeuvresV());
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        destinationPage = "listerOeuvre";
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "form_modifierOeuvre.htm")
    public ModelAndView formModifierOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // On récupère tous les propriétaires
            ProprietaireService proprietaireService = new ProprietaireService();
            OeuvreVenteService oeuvreVenteService = new OeuvreVenteService();
            request.setAttribute("mesProp", proprietaireService.consulterListeProp());

            // On récupère les caractéristiques de l'oeuvre
            request.setAttribute("oeuvre", oeuvreVenteService.rechercherOeuvreIdParam(Integer.valueOf(request.getParameter("idOeuvre"))));
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        destinationPage = "modifierOeuvre";
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modifierOeuvre.htm")
    public ModelAndView modifierOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            //Création du service
            OeuvreVenteService oeuvreVenteService = new OeuvreVenteService();
            ProprietaireService proprietaireService = new ProprietaireService();

            //Récupération de l'oeuvre à modifier
            int idOeuvrevente = Integer.valueOf(request.getParameter("idOeuvre"));
            Oeuvrevente oeuvrevente = oeuvreVenteService.rechercherOeuvreIdParam(idOeuvrevente);

            String titreOeuvrevente = request.getParameter("txttitre").toString();
            oeuvrevente.setTitreOeuvrevente(titreOeuvrevente);

            float prixOeuvrevente = Float.valueOf(request.getParameter("txtprix"));
            oeuvrevente.setPrixOeuvrevente(prixOeuvrevente);

            Proprietaire propOeuvrevente = proprietaireService.rechercherProprietaire(Integer.valueOf(request.getParameter("prop")));
            oeuvrevente.setProprietaire(propOeuvrevente);

            oeuvreVenteService.modifierOeuvre(oeuvrevente);
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        destinationPage = "home";
        return new ModelAndView(destinationPage);
    }
}
