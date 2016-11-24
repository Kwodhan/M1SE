package part2;
/**
 * 
 * @author Antoine FEREY
 *
 */
public class Guichet {

	/**
	 * Pointeur pour distribuer les groupes
	 */
	public static Groupe groupe;
	/**
	 * Numéro du groupe
	 */
	public static int k;
	/**
	 * numéro du guichet
	 */
	private final int num;

	public Guichet(int num) {

		Guichet.k = 0;
		Guichet.groupe = new Groupe("G" + 0);
		this.num = num;
	}

	/**
	 * Inscription du client dans un groupe
	 * 
	 * @param c
	 */
	public synchronized void inscription(Client c) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Guichet.add(c);

		System.out.println("Client : " + c.getNom() + " Groupe " + c.getGroupe().getNom()
				+ " Action : Inscrition sur le stand " + this.num);

	}

	/**
	 * Le client c paye
	 * 
	 * @param c
	 */
	public synchronized void paiement(Client c) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Client : " + c.getNom() + " Groupe " + c.getGroupe().getNom()
				+ " Action : paiement sur le stand " + this.num);

	}

	/**
	 * Operation static pour ajouter des clients
	 */
	public static synchronized void add(Client c) {
		if (Guichet.groupe.isComplete()) {
			System.out.println("Le groupe " + Guichet.groupe.getNom() + " est plein");
			Guichet.k++;
			Guichet.groupe = new Groupe("G" + (Guichet.k));
		}
		Guichet.groupe.addClient(c);
	}

}
