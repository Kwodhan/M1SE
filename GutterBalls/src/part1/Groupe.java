package part1;

import java.util.ArrayList;
/**
 * 
 * @author Antoine FEREY
 *
 */
public class Groupe {
	/**
	 * La capacité max du groupe
	 */
	private final int capacite;
	/**
	 * Num de la piste Si le groupe n'a pas de piste numPiste = -1
	 */
	private PisteBowling piste;
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
		capacite = 2;
		piste = null;

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
	 * fait attendre tant que le groupe n'est pas complet
	 */
	public synchronized void attendreResteGroupe() {
		while (!this.isComplete()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();
	}
	/**
	 * fait attendre tant que tous les membres n'ont pas leurs chaussures
	 */
	public synchronized void attendreResteChaussure() {
		while (!this.tousChaussuresBowling()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();
	}
	/**
	 * fait attendre tant que tous les membres ne sont pas sur la piste
	 */
	public synchronized void attendreRestePiste() {
		while (!this.tousSurPiste()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();
	}
	/**
	 * fait attendre tant que tous les membres n'ont pas jouer
	 */
	public synchronized void attendreResteJouer() {
		while (!this.tousjouer()) { // tant que tout le monde n'a pas joué, on
									// attend
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		notifyAll(); // notifie que tout le monde vient de jouer, on peut partir
						// de ce bowling

	}
	/**
	 * Si tout le monde posséde des chaussures de bowling renvoie true
	 * 
	 * @return
	 */
	private synchronized boolean tousChaussuresBowling() {
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
	private synchronized boolean tousSurPiste() {
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
	 * 
	 * @return
	 */
	private synchronized boolean tousjouer() {
		boolean res = true;
		for (Client c : clients) {
			res = res && c.isJouer();
		}
		return res;
	}

	public synchronized void setNumPiste(PisteBowling piste) {
		this.piste = piste;
	}

	public synchronized PisteBowling getPisteReservee() {
		return piste;
	}

	public synchronized String getNom() {
		return nom;
	}

	



}
