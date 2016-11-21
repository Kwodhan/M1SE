package part1;
/**
 * 
 * @author Antoine FEREY
 *
 */
public class Client implements Runnable {
	/**
	 * Le bowling ou le client est
	 */
	private Bowling b;
	/**
	 * son groupe null si il n'est pas dans un groupe
	 */
	private Groupe g;
	/**
	 * Son nom. exemple : c1
	 */
	private String nom;
	/**
	 * si il y a ses chaussures de bowling
	 */
	private boolean chaussureBowling;
	/**
	 * Si il est sur une piste
	 */
	private boolean surPiste;
	/**
	 * si il est entrain de jouer au bowling
	 */
	private boolean jouer;

	public Client(Bowling b, String nom) {
		this.b = b;
		this.nom = nom;
		this.g = null;
		this.chaussureBowling = false;
		this.surPiste = false;
		this.jouer = false;

	}

	@Override
	public void run() {
		b.arriverClient(this);
	}

	public synchronized boolean possedeChaussureBowling() {
		return chaussureBowling;
	}

	public synchronized void recevoirChaussuresBowling() {
		this.chaussureBowling = true;
	}

	public synchronized void rendreChaussuresBowling() {
		this.chaussureBowling = false;
	}

	public synchronized void setGroupe(Groupe g) {
		this.g = g;
	}

	public synchronized boolean estSurLaPiste() {
		return surPiste;
	}

	public synchronized void entrerPiste() {
		surPiste = true;
	}

	public synchronized void quitterPiste() {
		surPiste = false;
	}

	public synchronized void jouer() {
		jouer = true;
	}

	public synchronized void finirjouer() {
		jouer = false;
	}

	public synchronized Groupe getGroupe() {
		return this.g;
	}

	public synchronized String getNom() {
		return nom;
	}

	public boolean isJouer() {
		return jouer;
	}

}
