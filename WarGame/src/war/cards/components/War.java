package war.cards.components;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class War {

	private Deck player1Deck;
	private Deck player2Deck;
	private Card player1CurrentCard;
	private Card player2CurrentCard;
	

	public War() 
	{
		Deck deck = new Deck();
		deck.shuffle();
		player1Deck = new Deck(deck.split(0, 25));
		player2Deck = new Deck(deck.split(26, 51));
		round();
	}
	
	public void round()
	{
		player1CurrentCard = player1Deck.deal();
		player2CurrentCard = player2Deck.deal();
		int winner = whoWon();
		if (winner == 1)
		{
			player1Deck.add(player2Deck.remove());
		}
		else if (winner == -1)
		{
			player2Deck.add(player1Deck.remove());
		}
	}
	
	public Card getPlayer1CurrentCard()
	{
		return player1CurrentCard;
	}

	public Card getPlayer2CurrentCard()
	{
		return player2CurrentCard;
	}
	
	public int getPlayer1DeckSize()
	{
		return player1Deck.getSize();
	}
	
	public int getPlayer2DeckSize()
	{
		return player2Deck.getSize();
	}
	
	public int whoWon()
	{
		return player1CurrentCard.compareTo(player2CurrentCard);
	}
	//
	//		while (true)
	//		{
	//			System.out.println("Enter to press a round");
	//			Scanner readUserInput=new Scanner(System.in);
	//			readUserInput.nextLine();  
	//			Card player1Card = player1Deck.deal();
	//			Card player2Card = player2Deck.deal();
	//			System.out.println("Player 1: " + player1Card.toString() + ", Player 2: "+  player2Card.toString());
	//			int compareValue = player1Card.compareTo(player2Card);
	//			if (compareValue == 1)
	//				System.out.println("Player 1 wins!");
	//			else if (compareValue == -1)
	//				System.out.println("Player 2 wins!");
	//			else
	//				System.out.println("No one wins!");
	//		}
	//
	//
	//	}

}
