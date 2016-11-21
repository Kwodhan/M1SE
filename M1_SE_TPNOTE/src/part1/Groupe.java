package part1;

import java.util.ArrayList;

public class Groupe {
	/**
	 * La capacité max du groupe
	 */
	private final int capacite;
	/**
	 * Num de la piste Si le groupe n'a pas de piste numPiste = -1
	 */
	private int numPiste;
	/**
	 * nom du groupe. exemple : G0
	 */
	private final String nom;
	/**
	 * Liste des clients
	 */

	private ArrayList<Client> clients;

	public Groupe(String nom) {
		this.nom = nom;
		this.clients = new ArrayList<Client>();
		capacite = 4;
		numPiste = -1;

	}

	/**
	 * Si le groupe est complet renvoie true
	 * 
	 * @return
	 */
	public synchronized boolean isComplete() {
		return this.clients.size() == capacite;
	}

	/**
	 * Ajoute un client
	 * 
	 * @param c
	 */
	public synchronized void addClient(Client c) {

		this.clients.add(c);
		c.setGroupe(this);

	}

	/**
	 * Si tout le monde posséde des chaussures de bowling renvoie true
	 * 
	 * @return
	 */
	public synchronized boolean tousChaussuresBowling() {
		boolean res = true;
		for (Client c : clients) {
			res = res && c.possedeChaussureBowling();
		}

		return res;
	}

	/**
	 * Si tout le monde est sur la piste renvoie true
	 * 
	 * @return
	 */
	public synchronized boolean tousSurPiste() {
		boolean res = true;
		for (Client c : clients) {
			res = res && c.estSurLaPiste();
		}
		return res;
	}

	/**
	 * Si personne est sur la piste renvoie true
	 * 
	 * @return
	 */
	public synchronized boolean personneSurPiste() {
		boolean res = false;
		for (Client c : clients) {
			res = res || c.estSurLaPiste();
		}
		return !res;
	}
	/**
	 * Si tout le monde a jouer renvoie true
	 * @return
	 */
	public synchronized boolean tousjouer() {
		boolean res = true;
		for (Client c : clients) {
			res = res && c.isJouer();
		}
		return res;
	}
	
	public synchronized void setNumPiste(int numPiste) {
		this.numPiste = numPiste;
	}

	public synchronized int pisteReservee() {
		return numPiste;
	}

	public String getNom() {
		return nom;
	}

}
