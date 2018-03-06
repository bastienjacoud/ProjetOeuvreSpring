package com.epul.oeuvres.controle;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.*;
import com.epul.oeuvres.metier.*;

///
/// Les m�thode du contr�leur r�pondent � des sollicitations
/// des pages JSP

@Controller
public class MultiControleurOeuvre {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

    @RequestMapping(value = "modifierOeuvre.htm")
    public ModelAndView modifierOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage;
        try {
            //Création du service
            Service unService = new Service();

            //Récupération de l'oeuvre à modifier
            int idOeuvrevente = Integer.valueOf(request.getParameter("idOeuvre"));
            Oeuvrevente oeuvrevente = unService.rechercherOeuvreIdParam(idOeuvrevente);

            String titreOeuvrevente = request.getParameter("txttitre").toString();
            oeuvrevente.setTitreOeuvrevente(titreOeuvrevente);

            float prixOeuvrevente = Float.valueOf(request.getParameter("txtprix"));
            oeuvrevente.setPrixOeuvrevente(prixOeuvrevente);

            Proprietaire propOeuvrevente = unService.rechercherProprietaire(Integer.valueOf(request.getParameter("prop")));
            oeuvrevente.setProprietaire(propOeuvrevente);

            unService.modifierOeuvre(oeuvrevente);
            destinationPage = "home";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "/form_modifierOeuvre.htm", params = {"idOeuvre"})
    public ModelAndView formModifierOeuvre(@RequestParam(value = "idOeuvre") int idOeuvreVente, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // On récupère tous les propriétaires
            Service unService = new Service();
            request.setAttribute("mesProp", unService.consulterListeProp());

            // On récupère les caractéristiques de l'oeuvre
            request.setAttribute("oeuvre", unService.rechercherOeuvreIdParam(idOeuvreVente));
            destinationPage = "modifierOeuvre";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "listerOeuvre.htm")
    public ModelAndView listerOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            Service unService = new Service();
            request.setAttribute("mesOeuvresV", unService.consulterListeOeuvresV());
            destinationPage = "listerOeuvre";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "ajouterOeuvre")
    public ModelAndView AjouteOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            Service unService = new Service();
            request.setAttribute("mesProprietaires", unService.consulterListeProprietaires());
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
            Service unService = new Service();
            Oeuvrevente oeuvrevente = new Oeuvrevente();
            oeuvrevente.setTitreOeuvrevente(request.getParameter("titre"));
            oeuvrevente.setEtatOeuvrevente("L");
            oeuvrevente.setPrixOeuvrevente(Integer.valueOf(request.getParameter("prix")));
            oeuvrevente.setProprietaire(unService.rechercherProprietaire(Integer.valueOf(request.getParameter("idProprietaire"))));
            unService.insertOeuvreVente(oeuvrevente);
            destinationPage = "home";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }
}
