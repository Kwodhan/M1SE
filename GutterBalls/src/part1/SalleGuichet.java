package part1;

public class SalleGuichet {

	/**
	 * Pointeur pour distribuer les groupes
	 */
	public  Groupe groupe;
	/**
	 * Numéro du groupe
	 */
	public  int k;


	public SalleGuichet() {

		k = 0;
		this.groupe = new Groupe("G" + 0);

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

		if (this.groupe.isComplete()) {
			System.out.println("Le groupe " + this.groupe.getNom() + " est plein");
			this.k++;
			this.groupe = new Groupe("G" + (this.k));
		}
		this.groupe.addClient(c);

		System.out.println("Client : " + c.getNom() + " Groupe " + c.getGroupe().getNom()
				+ " Action : Inscrition ");

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
				+ " Action : paiement ");

	}

	

}
