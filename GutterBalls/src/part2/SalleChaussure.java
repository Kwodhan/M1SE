package part2;

public class SalleChaussure {
	/**
	 * le client prend ses chaussures de bowling
	 * 
	 * @param c
	 */
	public synchronized void prendreChaussureBowling(Client c) {
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client : "+c.getNom() +" Groupe "+c.getGroupe().getNom()+" Action : Recevoir chaussure");
		c.recevoirChaussuresBowling();

		

	}

	/**
	 * Le client rend ses chaussures de bowling
	 * 
	 * @param c
	 */
	public synchronized void rendreChaussureBowling(Client c) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client : "+c.getNom() +" Groupe "+c.getGroupe().getNom()+" Action : Rendre chaussure");
		c.rendreChaussuresBowling();

	}
}
