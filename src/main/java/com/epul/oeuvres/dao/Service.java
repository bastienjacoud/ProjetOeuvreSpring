package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;

import java.text.SimpleDateFormat;
import java.util.*;

import com.epul.oeuvres.metier.*;
import com.epul.oeuvres.persistance.*;

public class Service {

	// Mise � jour des caract�ristiques d'un adh�rent
	// Le booleen indique s'il s'agit d'un nouvel adh�rent, auquel cas on fait
	// une cr�ation

	public void insertAdherent(Adherent unAdherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "insert into adherent  (nom_adherent,prenom_adherent,ville_adherent)  " + "values ('"
					+ unAdherent.getNomAdherent();
			mysql += "'" + ",'" + unAdherent.getPrenomAdherent() + "','" + unAdherent.getVilleAdherent() + "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	// gestion des adherents
	// Consultation d'un adh�rent par son num�ro
	// Fabrique et renvoie un objet adh�rent contenant le r�sultat de la requ�te
	// BDD
	public Adherent consulterAdherent(int numero) throws MonException {

		 Map mParams = new HashMap();
	     Map mParam;
	  try
	  {
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
	  } catch (MonException e)
		{
			throw e;
		}
	}

	// Consultation des adh�rents
	// Fabrique et renvoie une liste d'objets adh�rent contenant le r�sultat de
	// la requ�te BDD
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
			if(mParams == null){
				rs = unDialogueBd.lecture(mysql);
			}else{
				rs=DialogueBd.getInstance().lectureParametree(mysql, mParams);
			}
			while (index < rs.size()) {
				// On cr�e un stage
				Adherent unA = new Adherent();
				// il faut redecouper la liste pour retrouver les lignes
				unA.setIdAdherent(Integer.parseInt(rs.get(index + 0).toString()));
				unA.setNomAdherent(rs.get(index + 1).toString());
				unA.setPrenomAdherent(rs.get(index + 2).toString());
				unA.setVilleAdherent(rs.get(index + 3).toString());
				// On incr�mente tous les 3 champs
				index = index + 4;
				mesAdherents.add(unA);
			}

			return mesAdherents;
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}
	
	
	public Oeuvrevente rechercherOeuvreIdParam(int id) throws MonException
	{
		
		String mysql = "";
		 Map mParams = new HashMap();
	     Map mParam;
	 	List<Object> rs;
		Oeuvrevente uneOeuvre=null;;
		try
		{
			mysql = "SELECT id_oeuvrevente, titre_oeuvrevente, etat_oeuvrevente,prix_oeuvrevente,id_proprietaire";
			mysql += " FROM Oeuvrevente WHERE id_Oeuvrevente = ? ";
		     mParam = new HashMap();
		     mParam.put(1, id);
		     mParams.put(0, mParam);  
		     rs=DialogueBd.getInstance().lectureParametree(mysql, mParams);
		     if (rs.size() > 0) {
					
					uneOeuvre = new Oeuvrevente();
					uneOeuvre.setIdOeuvrevente(Integer.parseInt(rs.get(0).toString()));
					uneOeuvre.setTitreOeuvrevente(rs.get(1).toString());
					uneOeuvre.setEtatOeuvrevente(rs.get(2).toString());
					uneOeuvre.setPrixOeuvrevente(Float.parseFloat(rs.get(3).toString()));
					int num = Integer.parseInt(rs.get(4).toString());
					// On appelle la recherche d'un propri�taire
					uneOeuvre.setProprietaire(rechercherProprietaire(num));
				}
		} 
		
		catch (MonException e)
		{
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
		return uneOeuvre;
		
	}

	// Consultation des oeuvresVente
	// Fabrique et renvoie une liste d'objets oeuvreVente contenant le r�sultat de
	// la requ�te BDD
	public List<Oeuvrevente> consulterListeOeuvresV() throws MonException {
		String mysql = "select * from oeuvrevente";
		return consulterListeOeuvresV(mysql);
	}

	private List<Oeuvrevente> consulterListeOeuvresV(String mysql) throws MonException {
		List<Object> rs;
		List<Oeuvrevente> mesOeuvresV = new ArrayList<Oeuvrevente>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs =unDialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On cr�e un stage
				Oeuvrevente OV = new Oeuvrevente();
				// il faut redecouper la liste pour retrouver les lignes
				OV.setIdOeuvrevente(Integer.parseInt(rs.get(index + 0).toString()));
				OV.setTitreOeuvrevente(rs.get(index + 1).toString());
				OV.setEtatOeuvrevente(rs.get(index + 2).toString());
				OV.setPrixOeuvrevente(Float.parseFloat(rs.get(index + 3).toString()));
				int num = Integer.parseInt(rs.get(index + 4).toString());
				// On appelle la recherche d'un propri�taire
				OV.setProprietaire(rechercherProprietaire(num));
				// On incr�mente tous les 3 champs
				index = index + 5;
				mesOeuvresV.add(OV);
			}
			return mesOeuvresV;
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	// Consultation des propriétaires
	// Fabrique et renvoie une liste d'objets propriétaire contenant le r�sultat de
	// la requ�te BDD
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
			rs =unDialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On cr�e un stage
				Proprietaire prop = new Proprietaire();
				// il faut redecouper la liste pour retrouver les lignes
				prop.setIdProprietaire(Integer.parseInt(rs.get(index + 0).toString()));
				prop.setNomProprietaire(rs.get(index + 1).toString());
				prop.setPrenomProprietaire(rs.get(index + 2).toString());

				// On incr�mente tous les 3 champs
				index = index + 3;
				mesProp.add(prop);
			}
			return mesProp;
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
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
	 
	public Proprietaire rechercherProprietaire(int  id) throws MonException
	{

		Map mParams = new HashMap();
		Map mParam;
	 	List<Object> rs;
		Proprietaire  unProprietaire=null;
		String requete = "select * from Proprietaire where id_Proprietaire = ?";
		try 
		{
			 mParam = new HashMap();
		     mParam.put(1, id);
		     mParams.put(0, mParam);  
		     rs=DialogueBd.getInstance().lectureParametree(requete, mParams);
			if (rs.size() > 0) {
			
				unProprietaire = new Proprietaire();
				
				unProprietaire.setIdProprietaire(Integer.parseInt(rs.get(0).toString()));
				unProprietaire.setNomProprietaire(rs.get(1).toString());
				unProprietaire.setPrenomProprietaire(rs.get(2).toString());
			}
		}

		catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
		return unProprietaire;
	}

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

    public void modifierOeuvre(Oeuvrevente oeuvre) throws MonException
	{
		String mysql;
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try
		{
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
