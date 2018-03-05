package utils;

public class Game {

	public static void main(String args[]) {
		// Agent A chooses a random number
		// Agent B tries to guess it
		System.out.println("Agent B wants to start the game");
		
		// Agent A generates a random number and sends the interval to Agent B
		
		int inf = 5; int sup = 25;
		
		int hidden = (int) (Math.random() * (sup-inf)) + inf;
		
		System.out.println(" inf : " + inf + " sup : " + sup);
		
		int guess = -1;
		int count = 0;
		while(true) {
			System.out.println("Guessing number between : " + inf + " and " + sup);
			guess = (int) (Math.random() * (sup-inf)) + inf;
			count++;
			
			if(guess < hidden ) inf = guess;
			if(guess > hidden ) sup = guess;
			if (guess == hidden) {
				System.out.println("Correct ! : guess : " + guess + " hidden : " + hidden);
				System.out.println("Number of guesses : " + count);
				break;
			}
		}
		
	}
}
