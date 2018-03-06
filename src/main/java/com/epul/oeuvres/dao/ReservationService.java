package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Reservation;
import com.epul.oeuvres.persistance.DialogueBd;

import java.text.SimpleDateFormat;

/**
 * Created by clementserrano on 06/03/2018.
 */
public class ReservationService {
    public void reserverOeuvre(Reservation reservation) throws MonException {
        String mysql;
        DialogueBd unDialogueBd = DialogueBd.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Inserérer une réservation
            mysql = "insert into reservation (id_oeuvrevente,id_adherent,date_reservation, statut)  " + "values ('"
                    + reservation.getOeuvrevente().getIdOeuvrevente() + "','"
                    + reservation.getAdherent().getIdAdherent() + "','"
                    + sdf.format(reservation.getDate()) + "','"
                    + reservation.getStatus() + "')";
            unDialogueBd.insertionBD(mysql);

            // Modifier l'état
            mysql = "update oeuvrevente set etat_oeuvrevente = 'R' where id_oeuvrevente = '"
                    + reservation.getOeuvrevente().getIdOeuvrevente() + "'";
            unDialogueBd.insertionBD(mysql);
        } catch (MonException e) {
            throw e;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }
}
