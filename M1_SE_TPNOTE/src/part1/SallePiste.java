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
	private int ticket;
	public SallePiste() {
		super();
		this.pistes = new ArrayList<>();
		ticket=0;
		for (int i = 0; i < 3; i++) {
			this.pistes.add(i, new PisteBowling(i));
		}

	}

	/**
	 * rejoindre une piste
	 * @param c
	 */
	public synchronized void prendrepiste(Client c) {
		System.out.println();
		// tant qu'il n'y a pas de piste libre et que je n'ai pas de piste attribué --> je danse
		while ((c.getGroupe().pisteReservee() == -1 && c.getGroupe().getNum()!=ticket) ||  (!pisteLibre() && c.getGroupe().pisteReservee() == -1 && c.getGroupe().getNum()==ticket)) { 
			danser(c);
			
		}

		if (c.getGroupe().pisteReservee() == -1) { 	// si le groupe n'a pas de piste, un client du groupe prend une piste libre
			ticket++;
			int pisteLibre = quellePisteLibre();
			System.out.println("La piste " + pisteLibre + " est libre , le client " + c.getNom() + "du groupe "
					+ c.getGroupe().getNom() + " occupe cette piste");

			jouer(c, pisteLibre);
		} else {									// sinon le client rejoint son groupe sur la piste

			System.out.println("Le client " + c.getNom() + " rejoint son groupe " + c.getGroupe().getNom()
					+ " sur la piste " + c.getGroupe().pisteReservee());
			jouer(c, c.getGroupe().pisteReservee());
		}
	}

	
	public synchronized void jouer(Client c, int numPiste) {
		PisteBowling p = pistes.get(numPiste);
		p.entrerPiste(c);
		c.getGroupe().setNumPiste(numPiste);
		notifyAll(); // notifie au autre client du groupe qu'il y a une piste qui est prise

		while (!c.getGroupe().tousSurPiste()) { // tant que tout le monde n'est pas sur la piste, on attend

			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll(); // notifie que tout le monde est sur la piste, on va pouvoir jouer
		System.out
				.println("Le client " + c.getNom() + " du groupe " + c.getGroupe().getNom() + " joue enfin au bowling");

		p.jouer(c);

		
		while (!c.getGroupe().tousjouer()) { // tant que tout le monde n'a pas joué, on attend
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		notifyAll(); // notifie que tout le monde vient de jouer, on peut partir de ce bowling

		p.quitter(c);

		if (p.estLibre()) {
			System.out.println("Le groupe " + c.getGroupe().getNom() + " vient de liberer la piste " + p.getNumero());
			notifyAll();//on notifie les autres groupes qu'une piste vient de se libérer

		}

	}

	public synchronized void danser(Client c) {
		System.out.println(c.getNom() + " danse du groupe " + c.getGroupe().getNom());
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Si il y a une piste de libre renvoie true
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
