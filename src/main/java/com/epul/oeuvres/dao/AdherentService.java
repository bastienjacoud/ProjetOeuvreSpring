package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Adherent;
import com.epul.oeuvres.persistance.DialogueBd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clementserrano on 06/03/2018.
 */
public class AdherentService {

    /* Mise à jour des caractéristiques d'un adhérent
    Le booleen indique s'il s'agit d'un nouvel adhérent, auquel cas on fait
    une création */
    public void insertAdherent(Adherent unAdherent) throws MonException {
        String mysql;
        DialogueBd unDialogueBd = DialogueBd.getInstance();
        try {
            mysql = "insert into adherent (nom_adherent,prenom_adherent,ville_adherent) " + "values ('"
                    + unAdherent.getNomAdherent();
            mysql += "'" + ",'" + unAdherent.getPrenomAdherent() + "','" + unAdherent.getVilleAdherent() + "')";
            unDialogueBd.insertionBD(mysql);
        } catch (MonException e) {
            throw e;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

    /* Gestion des adherents
    Consultation d'un adhérent par son numéro
    Fabrique et renvoie un objet adhérent contenant le résultat de la requête
    BDD */
    public Adherent consulterAdherent(int numero) throws MonException {
        Map mParams = new HashMap();
        Map mParam;
        try {
            String mysql = "select * from adherent where id_adherent = ?";
            mParam = new HashMap();
            mParam.put(1, numero);
            mParams.put(0, mParam);
            List<Adherent> mesAdh = consulterListeAdherents(mysql, mParams);
            if (mesAdh.isEmpty())
                return null;
            else {
                return mesAdh.get(0);
            }
        } catch (MonException e) {
            throw e;
        }
    }

    /* Consultation des adhérents
    Fabrique et renvoie une liste d'objets adhérent contenant le résultat de
    la requête BDD */
    public List<Adherent> consulterListeAdherents() throws MonException {
        String mysql = "select * from adherent";
        return consulterListeAdherents(mysql, null);
    }

    private List<Adherent> consulterListeAdherents(String mysql, Map mParams) throws MonException {
        List<Object> rs;
        List<Adherent> mesAdherents = new ArrayList<Adherent>();
        int index = 0;
        try {
            DialogueBd unDialogueBd = DialogueBd.getInstance();
            if (mParams == null) {
                rs = unDialogueBd.lecture(mysql);
            } else {
                rs = DialogueBd.getInstance().lectureParametree(mysql, mParams);
            }
            while (index < rs.size()) {
                // On crée un stage
                Adherent unA = new Adherent();
                // il faut redecouper la liste pour retrouver les lignes
                unA.setIdAdherent(Integer.parseInt(rs.get(index + 0).toString()));
                unA.setNomAdherent(rs.get(index + 1).toString());
                unA.setPrenomAdherent(rs.get(index + 2).toString());
                unA.setVilleAdherent(rs.get(index + 3).toString());
                // On incrémente tous les 3 champs
                index = index + 4;
                mesAdherents.add(unA);
            }
            return mesAdherents;
        } catch (MonException e) {
            throw e;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }
}
