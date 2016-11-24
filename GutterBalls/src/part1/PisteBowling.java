package part1;

public class PisteBowling {
	/**
	 * Si la piste est libre --> true
	 */
	private boolean estLibre;
	/**
	 * numéro de la piste
	 */
	private int numero;

	public PisteBowling(int numero) {
		estLibre = true;
		this.numero = numero;
	}

	/**
	 * un client joue
	 * 
	 * @param c
	 *            Le client
	 */
	public synchronized void jouer(Client c) {
		System.out.println("Client : " + c.getNom() + " Groupe " + c.getGroupe().getNom() + " Action : Jouer sur piste "
				+ this.numero);
		c.jouer();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public synchronized boolean estLibre() {
		return this.estLibre;
	}

	/**
	 * Le client c arrive sur la piste
	 * 
	 * @param c
	 *            Le Client
	 */
	public synchronized void entrerPiste(Client c) {
		estLibre = false;
		c.entrerPiste();
	}

	/**
	 * Le client c quite la piste
	 * 
	 * @param c
	 */
	public synchronized void quitter(Client c) {
		System.out.println("Client : " + c.getNom() + " Groupe " + c.getGroupe().getNom()
				+ " Action : Quitter la piste " + this.numero);
		c.quitterPiste();
		if (c.getGroupe().personneSurPiste()) // si il n'y a plus personne sur
												// la piste, on la libére
			estLibre = true;
	}

	public synchronized int getNumero() {
		return numero;
	}

}
