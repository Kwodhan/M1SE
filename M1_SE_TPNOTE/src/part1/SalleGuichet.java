package part1;

public class SalleGuichet {
	/**
	 * Pointeur pour distribuer les groupes
	 */
	private Groupe groupe;
	/**
	 * nom du groupe distribuer
	 */
	private int k;
	
	public SalleGuichet() {
		
		this.k=0;
		this.groupe = new Groupe("G"+k);
	}
	
	/**
	 * Inscription du client dans un groupe
	 * @param c
	 */
	public synchronized void inscription(Client c){
		System.out.println("Le client "+c.getNom() +" s'inscrit");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Le client "+c.getNom()+ " rejoind le groupe "+this.groupe.getNom());
		this.groupe.addClient(c);
		
		if(this.groupe.isComplete()){ // quand le groupe est complet, on en crée un nouveau
			System.out.println("Le groupe "+this.groupe.getNom()+ " est plein");
			this.groupe = new Groupe("G"+(++k));
		}
		
		
		while(!c.getGroupe().isComplete()) { //Le client attend tant que le groupe g n'est pas complet
		
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		notifyAll(); // notifie tout le monde que le groupe est complet
		
	}
	/**
	 * Le client c paye 
	 * @param c
	 */
	public synchronized void paiement(Client c){
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Le client "+c.getNom() +" de l'ancien groupe " +c.getGroupe().getNom()+ " paye");
	}
	
	
}
