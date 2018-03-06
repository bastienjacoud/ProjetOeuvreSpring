package com.epul.oeuvres.controle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Les méthodes du contrôleur répondent à des sollicitations
// des pages JSP

@Controller
public class MultiControleur {

    private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

    // Affichage de la page d'accueil
    @RequestMapping(value = "home.htm", method = RequestMethod.GET)
    public ModelAndView Afficheindex(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("home");
    }

    // Affichage de la page d'accueil
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView Afficheindex2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("home");
    }

    // Affichage de la page d'accueil
    @RequestMapping(value = "erreur.htm", method = RequestMethod.GET)
    public ModelAndView AfficheErreur(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("Erreur");
    }
}
