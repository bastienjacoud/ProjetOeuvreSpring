package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Oeuvrevente;
import com.epul.oeuvres.metier.Proprietaire;
import com.epul.oeuvres.persistance.DialogueBd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clementserrano on 06/03/2018.
 */
public class OeuvreVenteService {
    public Oeuvrevente rechercherOeuvreIdParam(int id) throws MonException {
        ProprietaireService proprietaireService = new ProprietaireService();
        String mysql = "";
        Map mParams = new HashMap();
        Map mParam;
        List<Object> rs;
        Oeuvrevente uneOeuvre = null;
        ;
        try {
            mysql = "SELECT id_oeuvrevente, titre_oeuvrevente, etat_oeuvrevente,prix_oeuvrevente,id_proprietaire";
            mysql += " FROM Oeuvrevente WHERE id_Oeuvrevente = ? ";
            mParam = new HashMap();
            mParam.put(1, id);
            mParams.put(0, mParam);
            rs = DialogueBd.getInstance().lectureParametree(mysql, mParams);
            if (rs.size() > 0) {
                uneOeuvre = new Oeuvrevente();
                uneOeuvre.setIdOeuvrevente(Integer.parseInt(rs.get(0).toString()));
                uneOeuvre.setTitreOeuvrevente(rs.get(1).toString());
                uneOeuvre.setEtatOeuvrevente(rs.get(2).toString());
                uneOeuvre.setPrixOeuvrevente(Float.parseFloat(rs.get(3).toString()));
                int num = Integer.parseInt(rs.get(4).toString());
                // On appelle la recherche d'un propri�taire
                uneOeuvre.setProprietaire(proprietaireService.rechercherProprietaire(num));
            }
        } catch (MonException e) {
            throw e;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
        return uneOeuvre;

    }

    /* Consultation des oeuvresVente
    Fabrique et renvoie une liste d'objets oeuvreVente contenant le résultat de
    la requête BDD */
    public List<Oeuvrevente> consulterListeOeuvresV() throws MonException {
        String mysql = "select * from oeuvrevente";
        return consulterListeOeuvresV(mysql);
    }

    private List<Oeuvrevente> consulterListeOeuvresV(String mysql) throws MonException {
        ProprietaireService proprietaireService = new ProprietaireService();
        List<Object> rs;
        List<Oeuvrevente> mesOeuvresV = new ArrayList<Oeuvrevente>();
        int index = 0;
        try {
            DialogueBd unDialogueBd = DialogueBd.getInstance();
            rs = unDialogueBd.lecture(mysql);
            while (index < rs.size()) {
                // On crée un stage
                Oeuvrevente OV = new Oeuvrevente();
                // il faut redecouper la liste pour retrouver les lignes
                OV.setIdOeuvrevente(Integer.parseInt(rs.get(index + 0).toString()));
                OV.setTitreOeuvrevente(rs.get(index + 1).toString());
                OV.setEtatOeuvrevente(rs.get(index + 2).toString());
                OV.setPrixOeuvrevente(Float.parseFloat(rs.get(index + 3).toString()));
                int num = Integer.parseInt(rs.get(index + 4).toString());
                // On appelle la recherche d'un propriétaire
                OV.setProprietaire(proprietaireService.rechercherProprietaire(num));
                // On incrémente tous les 3 champs
                index = index + 5;
                mesOeuvresV.add(OV);
            }
            return mesOeuvresV;
        } catch (MonException e) {
            throw e;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

    public void insertOeuvreVente(Oeuvrevente oeuvrevente) {
        String mysql;
        DialogueBd unDialogueBd = DialogueBd.getInstance();
        try {
            mysql = "insert into oeuvrevente (titre_oeuvrevente, etat_oeuvrevente, prix_oeuvrevente, id_proprietaire) " + "values ('"
                    + oeuvrevente.getTitreOeuvrevente() + "','"
                    + oeuvrevente.getEtatOeuvrevente() + "','"
                    + oeuvrevente.getPrixOeuvrevente() + "','"
                    + oeuvrevente.getProprietaire().getIdProprietaire() + "')";
            unDialogueBd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public void modifierOeuvre(Oeuvrevente oeuvre) throws MonException {
        String mysql;
        DialogueBd unDialogueBd = DialogueBd.getInstance();
        try {
            mysql = "update oeuvrevente set titre_oeuvrevente = " + "'" + oeuvre.getTitreOeuvrevente() + "'"
                    + ", prix_oeuvrevente = " + "'" + oeuvre.getPrixOeuvrevente() + "'"
                    + ", id_proprietaire = " + "'" + oeuvre.getProprietaire().getIdProprietaire() + "'"
                    + " where id_oeuvrevente = " + "'" + oeuvre.getIdOeuvrevente() + "'" + ";";
            unDialogueBd.insertionBD(mysql);
        } catch (MonException e) {
            throw e;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }
}
