package com.epul.oeuvres.controle;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "ajouterOeuvre", method = RequestMethod.GET)
    public ModelAndView AjouteOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            destinationPage = "ajouterOeuvre";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }
}
