package part2;

import java.util.ArrayList;
import java.util.Random;
/**
 * Salle qui distribue les clients dans l'un des 3 guichets
 * @author Antoine FEREY
 *
 */
public class SalleGuichet {
	/**
	 * Liste des guichets
	 */
	public ArrayList<Guichet> guichets = new ArrayList<Guichet>();

	public SalleGuichet() {
		
		for (int i = 0; i < 3; i++) {
			guichets.add(new Guichet(i));
		}
	}

	public void inscription(Client c) {

		Random rand = new Random();
		
		this.guichets.get(rand.nextInt(3)).inscription(c);
		
	}

	public void paiement(Client c) {
		Random rand = new Random();
		this.guichets.get(rand.nextInt(3)).paiement(c);
		
	}
	
	

}
