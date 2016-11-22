package part1;

import java.util.ArrayList;

public class SalleGuichet {
	/**
	 * Pointeur pour distribuer les groupes
	 */
	public static Groupe groupe;

	public ArrayList<Guichetier> guichets = new ArrayList<Guichetier>();

	public SalleGuichet() {

		SalleGuichet.groupe = new Groupe("G" + 0);
		for (int i = 0; i < 3; i++) {
			guichets.add(new Guichetier());
		}
	}

	public synchronized void inscription(Client c) {

		while (!guichetLibre()) { // tant qu'il n'y a pas de guichet libre on attend
									

			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		quelleGuichetLibre().inscription(c);
		notifyAll();// un guichet vient de se liberer
		while (!c.getGroupe().isComplete()) { // Le client attend tant que le groupe g soit complet
												
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		notifyAll(); // notifie tout les membres du groupe que le groupe est complet

	}

	public synchronized void paiement(Client c) {
		while (!guichetLibre()) { // tant qu'il n'y a pas de guichet libre on attend
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		quelleGuichetLibre().paiement(c);
		notifyAll();// un guichet vient de se liberer
	}

	/**
	 * Si il y a un guichet de libre renvoie true
	 * 
	 * @return
	 */
	public synchronized boolean guichetLibre() {
		boolean res = false;
		for (Guichetier p : guichets) {
			res = res || p.estLibre();
		}

		return res;
	}

	/**
	 * renvoie le guichetier libre
	 * 
	 * @return
	 */
	public synchronized Guichetier quelleGuichetLibre() {
		for (int i = 0; i < this.guichets.size(); i++) {
			if (this.guichets.get(i).estLibre())
				return this.guichets.get(i);
		}
		return null;

	}

}
