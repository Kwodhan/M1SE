package part1;

public class Guichetier {
	int k;
	private boolean estLibre;

	public Guichetier() {
		k = 0;
		this.estLibre=true;
	}

	/**
	 * Inscription du client dans un groupe
	 * 
	 * @param c
	 */
	public synchronized void inscription(Client c) {
		estLibre=false;
		System.out.println("Le client " + c.getNom() + " s'inscrit");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Le client " + c.getNom() + " rejoind le groupe " + SalleGuichet.groupe.getNom());
		SalleGuichet.groupe.addClient(c);
		estLibre=true;
		
		if (SalleGuichet.groupe.isComplete()) { // quand le groupe est complet,
												// on en crée un nouveau
			System.out.println("Le groupe " + SalleGuichet.groupe.getNom() + " est plein");
			SalleGuichet.groupe = new Groupe("G" + (++k));
		}
		
		

	}

	/**
	 * Le client c paye
	 * 
	 * @param c
	 */
	public synchronized void paiement(Client c) {
		estLibre=false;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Le client " + c.getNom() + " de l'ancien groupe " + c.getGroupe().getNom() + " paye");
		estLibre=true;
		
	}
	
	public synchronized boolean estLibre() {
		return this.estLibre;
	}
}
