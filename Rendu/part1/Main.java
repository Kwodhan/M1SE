package part1;

//benjamin.rouxel@inria.fr
public class Main {
	public static void main(String[] args) {

			
		
	
		Bowling b = new Bowling();
		
		for(int i = 0 ; i < 3*2 ; i++) {
			
			Client c = new Client(b,"c"+i);
			Thread th = new Thread(c);

			th.start();
		}
		
		
	}
}
