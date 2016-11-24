package part1;

/**
 * 
 * @author Antoine FEREY
 *
 */
public class Bowling {
	/**
	 * Salle ou il y a les pistes
	 */
	private SallePiste pistes;
	/**
	 * Salle ou on prend les chaussures de bowling et on rend les chaussures
	 */
	private SalleChaussure salleChaussure;
	/**
	 * Salle ou on crée un groupe et ou on pay en repartant
	 */

	private SalleGuichet guichet;

	/**
	 * constructeur de l'etablisement
	 */
	public Bowling() {

		this.salleChaussure = new SalleChaussure();
		this.guichet = new SalleGuichet();
		this.pistes = new SallePiste();

	}

	/**
	 * Chemin d'un client
	 * 
	 * @param c
	 *            Le client qui arrive
	 */
	public void arriverClient(Client c) {
		guichet.inscription(c);
		c.getGroupe().attendreResteGroupe();
		salleChaussure.prendreChaussureBowling(c);
		c.getGroupe().attendreResteChaussure();
		pistes.prendrepiste(c);
		c.getGroupe().attendreRestePiste();
		c.getGroupe().getPisteReservee().jouer(c);
		c.getGroupe().attendreResteJouer();
		pistes.rendrepiste(c);
		guichet.paiement(c);
		salleChaussure.rendreChaussureBowling(c);

	}

}
