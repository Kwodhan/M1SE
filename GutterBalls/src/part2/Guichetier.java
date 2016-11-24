package part2;

public class Guichetier {

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

	public Guichetier(int num) {

		Guichetier.k = 0;
		Guichetier.groupe = new Groupe("G" + 0);
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

		Guichetier.add(c);

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
		if (Guichetier.groupe.isComplete()) {
			System.out.println("Le groupe " + Guichetier.groupe.getNom() + " est plein");
			Guichetier.k++;
			Guichetier.groupe = new Groupe("G" + (Guichetier.k));
		}
		Guichetier.groupe.addClient(c);
	}

}
