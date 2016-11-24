package part2;

import java.util.ArrayList;
import java.util.Random;

public class SalleGuichet {
	
	public ArrayList<Guichetier> guichets = new ArrayList<Guichetier>();

	public SalleGuichet() {
		
		for (int i = 0; i < 3; i++) {
			guichets.add(new Guichetier(i));
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
