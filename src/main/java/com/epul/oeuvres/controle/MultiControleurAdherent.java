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
public class MultiControleurAdherent {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

	@RequestMapping(value = "listerAdherent.htm")
	public ModelAndView afficherLesStages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage;
		try {
			// HttpSession session = request.getSession();
			Service unService = new Service();
			request.setAttribute("mesAdherents", unService.consulterListeAdherents());
			destinationPage = "listerAdherent";
		} catch (MonException e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "Erreur";

		}
		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "insererAdherent.htm")
	public ModelAndView insererAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			Adherent unAdherent = new Adherent();
			unAdherent.setNomAdherent(request.getParameter("txtnom"));
			unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
			unAdherent.setVilleAdherent(request.getParameter("txtville"));
			Service unService = new Service();
			unService.insertAdherent(unAdherent);
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "Erreur";
		}
		destinationPage = "home";
		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "ajouterAdherent.htm")
	public ModelAndView ajouterAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			destinationPage = "ajouterAdherent";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "Erreur";
		}

		return new ModelAndView(destinationPage);
	}
}
