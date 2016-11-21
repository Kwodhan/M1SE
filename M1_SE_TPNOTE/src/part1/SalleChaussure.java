package part1;

public class SalleChaussure {
	/**
	 * le client prend ses chaussures de bowling
	 * @param c
	 */
	public synchronized void prendreChaussureBowling(Client c) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Le client "+c.getNom()+" prend ses chaussures");
		c.recevoirChaussuresBowling();
		
		while(!c.getGroupe().tousChaussuresBowling()){ // tant que tout le monde du groupe n'a pas ses chaussures, on attend
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll(); // notifie que tout le monde du groupe à ses chaussures
		
		
	}
	/**
	 * Le client rend ses chaussures de bowling
	 * @param c
	 */
	public synchronized void rendreChaussureBowling(Client c) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Le client "+c.getNom()+" rend ses chaussures");
		c.rendreChaussuresBowling();

	}
}
