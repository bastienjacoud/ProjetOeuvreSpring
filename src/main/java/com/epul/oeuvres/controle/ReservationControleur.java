package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.AdherentService;
import com.epul.oeuvres.dao.OeuvreVenteService;
import com.epul.oeuvres.dao.ReservationService;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Adherent;
import com.epul.oeuvres.metier.Oeuvrevente;
import com.epul.oeuvres.metier.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by clementserrano on 06/03/2018.
 */
@Controller
public class ReservationControleur {

    private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

    @RequestMapping(value = "reserverMenu.htm")
    public ModelAndView reserverMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            AdherentService adherentService = new AdherentService();
            OeuvreVenteService oeuvreVenteService = new OeuvreVenteService();

            // on retourne la liste des adhérents pour le select
            request.setAttribute("mesAdherents", adherentService.consulterListeAdherents());

            // on récupère l'oeuvre correspondant à l'id en paramètre
            Oeuvrevente oeuvrevente = oeuvreVenteService.rechercherOeuvreIdParam(
                    Integer.valueOf(request.getParameter("idOeuvre")));

            // on renvoi l'oeuvre pour initialiser les champs du formulaire
            request.setAttribute("oeuvre", oeuvrevente);
        } catch (MonException e) {
            e.printStackTrace();
        }
        destinationPage = "reserverOeuvre";
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "reserverOeuvre.htm")
    public ModelAndView reserverOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            AdherentService adherentService = new AdherentService();
            OeuvreVenteService oeuvreVenteService = new OeuvreVenteService();
            ReservationService reservationService = new ReservationService();

            Reservation reservation = new Reservation();

            // On récupère l'adhérent
            int idAdherent = Integer.valueOf(request.getParameter("idAdherent"));
            Adherent adherent = adherentService.consulterAdherent(idAdherent);

            // on récupère l'oeuvre
            int idOeuvrevente = Integer.valueOf(request.getParameter("idOeuvre"));
            Oeuvrevente oeuvrevente = oeuvreVenteService.rechercherOeuvreIdParam(idOeuvrevente);

            // On parse la date fournie
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = request.getParameter("date");
            Date date = null;
            try {
                date = sdf.parse(sDate);

                // Si le parse est OK, on réserve
                reservation.setAdherent(adherent);
                reservation.setDate(date);
                reservation.setOeuvrevente(oeuvrevente);
                reservation.setStatus("reservee");

                reservationService.reserverOeuvre(reservation);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (MonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        destinationPage = "forward:/listerOeuvre.htm";
        return new ModelAndView(destinationPage);
    }
}
