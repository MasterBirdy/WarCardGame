package war.cards.components;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class War {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		Deck player1Deck = new Deck(deck.split(0, 25));
		Deck player2Deck = new Deck(deck.split(26, 51));

		while (true)
		{
			System.out.println("Enter to press a round");
			Scanner readUserInput=new Scanner(System.in);
			readUserInput.nextLine();  
			Card player1Card = player1Deck.deal();
			Card player2Card = player2Deck.deal();
			System.out.println("Player 1: " + player1Card.toString() + ", Player 2: "+  player2Card.toString());
			int compareValue = player1Card.compareTo(player2Card);
			if (compareValue == 1)
				System.out.println("Player 1 wins!");
			else if (compareValue == -1)
				System.out.println("Player 2 wins!");
			else
				System.out.println("No one wins!");
		}


	}

}
