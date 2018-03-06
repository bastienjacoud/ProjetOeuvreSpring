package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Proprietaire;
import com.epul.oeuvres.persistance.DialogueBd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clementserrano on 06/03/2018.
 */
public class ProprietaireService {

    /* Consultation des propriétaires
    Fabrique et renvoie une liste d'objets propriétaire contenant le résultat de
    la requête BDD */
    public List<Proprietaire> consulterListeProp() throws MonException {
        String mysql = "select * from proprietaire";
        return consulterListeProp(mysql);
    }

    private List<Proprietaire> consulterListeProp(String mysql) throws MonException {
        List<Object> rs;
        List<Proprietaire> mesProp = new ArrayList<Proprietaire>();
        int index = 0;
        try {
            DialogueBd unDialogueBd = DialogueBd.getInstance();
            rs = unDialogueBd.lecture(mysql);
            while (index < rs.size()) {
                // On crée un stage
                Proprietaire prop = new Proprietaire();
                // il faut redecouper la liste pour retrouver les lignes
                prop.setIdProprietaire(Integer.parseInt(rs.get(index + 0).toString()));
                prop.setNomProprietaire(rs.get(index + 1).toString());
                prop.setPrenomProprietaire(rs.get(index + 2).toString());

                // On incrémente tous les 3 champs
                index = index + 3;
                mesProp.add(prop);
            }
            return mesProp;
        } catch (MonException e) {
            throw e;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

    public List<Proprietaire> consulterListeProprietaires() {
        String mysql = "select * from proprietaire";
        List<Object> rs;
        List<Proprietaire> mesProprietaires = new ArrayList<Proprietaire>();
        int index = 0;
        try {
            DialogueBd unDialogueBd = DialogueBd.getInstance();
            rs = unDialogueBd.lecture(mysql);
            while (index < rs.size()) {
                // On crée un stage
                Proprietaire unP = new Proprietaire();
                // il faut redecouper la liste pour retrouver les lignes
                unP.setIdProprietaire(Integer.parseInt(rs.get(index++).toString()));
                unP.setNomProprietaire(rs.get(index++).toString());
                unP.setPrenomProprietaire(rs.get(index++).toString());
                mesProprietaires.add(unP);
            }
        } catch (MonException e) {
            e.printStackTrace();
        }
        return mesProprietaires;
    }

    public Proprietaire rechercherProprietaire(int id) throws MonException {
        Map mParams = new HashMap();
        Map mParam;
        List<Object> rs;
        Proprietaire unProprietaire = null;
        String requete = "select * from Proprietaire where id_Proprietaire = ?";
        try {
            mParam = new HashMap();
            mParam.put(1, id);
            mParams.put(0, mParam);
            rs = DialogueBd.getInstance().lectureParametree(requete, mParams);
            if (rs.size() > 0) {
                unProprietaire = new Proprietaire();
                unProprietaire.setIdProprietaire(Integer.parseInt(rs.get(0).toString()));
                unProprietaire.setNomProprietaire(rs.get(1).toString());
                unProprietaire.setPrenomProprietaire(rs.get(2).toString());
            }
        } catch (MonException e) {
            throw e;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
        return unProprietaire;
    }
}
