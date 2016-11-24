package part1;

import java.util.ArrayList;
import java.util.List;

/**
 * Salle ou il y a toutes les pistes et la salle de danse
 * 
 * @author Antoine FEREY
 *
 */
public class SallePiste {

	private List<PisteBowling> pistes;

	public SallePiste() {
		super();
		this.pistes = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			this.pistes.add(i, new PisteBowling(i));
		}

	}

	/**
	 * rejoindre une piste
	 * 
	 * @param c
	 */
	public synchronized void prendrepiste(Client c) {

		// tant qu'il n'y a pas de piste libre et que je n'ai pas de piste
		// attribué --> je danse
		while ((!pisteLibre() && c.getGroupe().getPisteReservee() == null)) {
			danser(c);

		}

		if (c.getGroupe().getPisteReservee() == null) { // si le groupe n'a pas
														// de piste, un client
														// du groupe prend une
														// piste libre

			int pisteLibre = quellePisteLibre();
			System.out.println("Client : " + c.getNom() + " Groupe " + c.getGroupe().getNom()
					+ " Action : Prendre Piste " + pisteLibre);

			PisteBowling p = pistes.get(pisteLibre);
			p.entrerPiste(c);
			c.getGroupe().setNumPiste(p);

		} else { // sinon le client rejoint son groupe sur la piste
			System.out.println("Client : " + c.getNom() + " Groupe " + c.getGroupe().getNom()
					+ " Action : Rejoindre Piste " + c.getGroupe().getPisteReservee().getNumero());
			c.getGroupe().getPisteReservee().entrerPiste(c);

		}
	}

	/**
	 * Le client c quitte la piste
	 * 
	 * @param c
	 */
	public synchronized void rendrepiste(Client c) {
		c.getGroupe().getPisteReservee().quitter(c);
		notifyAll(); // notifie quand la piste est libre
	}

	public synchronized void danser(Client c) {
		System.out.println("Client : " + c.getNom() + " Groupe " + c.getGroupe().getNom() + " Action : Danser");
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Si il y a une piste de libre renvoie true
	 * 
	 * @return
	 */
	public synchronized boolean pisteLibre() {
		boolean res = false;
		for (PisteBowling p : pistes) {
			res = res || p.estLibre();
		}
		return res;
	}

	/**
	 * renvoie le numéro d'une piste libre
	 * 
	 * @return
	 */
	public synchronized int quellePisteLibre() {
		for (int i = 0; i < this.pistes.size(); i++) {
			if (this.pistes.get(i).estLibre())
				return i;
		}
		return -1;

	}
}
