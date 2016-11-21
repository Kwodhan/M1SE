package part1;
//@mail: benjamin.rouxel@inria.fr
public class Main {
	public static void main(String[] args) {
	
		
		Bowling b = new Bowling();
		
		for(int i = 0 ; i < 4*8 ; i++) {
			
			Client c = new Client(b,"c"+i);
			Thread th = new Thread(c);

			th.start();
		}
		
		
	}
}
