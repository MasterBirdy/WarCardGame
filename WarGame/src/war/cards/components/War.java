package war.cards.components;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class War {

	private Deck player1Deck;
	private Deck player2Deck;
	private Card player1CurrentCard;
	private Card player2CurrentCard;
	private List<Card> savedCards;
	private boolean warMode = false;
	private boolean firstTime;
	private int warTimes = 0;
	ArrayList<Card> oldCards = new ArrayList<Card>();


	public War() 
	{
		Deck deck = new Deck();
		deck.shuffle();
		player1Deck = new Deck(deck.split(0, 0));
		player2Deck = new Deck(deck.split(1, 51));
		firstTime = true;
		//player1Deck = new Deck(deck.split(0, 1));
		//player2Deck = new Deck(deck.split(2, 51));
		//round();
	}

	public void round()
	{
		player1CurrentCard = player1Deck.deal();
		player2CurrentCard = player2Deck.deal();
		int winner = whoWon();
		if (winner == 1)
		{
			player1Deck.add(player2Deck.remove());
			if (warMode)
			{
				for (int i = 0; i < warTimes * 3 ; i++)
					player1Deck.add(player2Deck.remove());
				warMode = false;
			}
		}
		else if (winner == -1)
		{
			player2Deck.add(player1Deck.remove());
			if (warMode)
			{
				for (int i = 0; i < warTimes * 3 ; i++)
					player2Deck.add(player1Deck.remove());
				warMode = false;
			}
		}
		else if (winner == 0)
		{
			warMode = true;
			warTimes++;
		}
		firstTime = false;
	}
	
	public boolean isFirstTime()
	{
		return firstTime;
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
	
	public List<Card> getOldCards()
	{
		oldCards.clear();
		if (whoWon() == 1)
		{
			for(int i = 0; i < warTimes * 3 + 1; i++ ) 
			{
				oldCards.add(player1Deck.getCard(player1Deck.getSize() - 1 - i));
			}
			if (!warMode)
			{
				warTimes = 0;
			}
		}
		else if (whoWon() == -1)
		{
			for(int i = 0; i < warTimes * 3 + 1; i++ )
			{
				oldCards.add(player2Deck.getCard(player2Deck.getSize() - 1 - i));
			}
			if (!warMode)
			{
				warTimes = 0;
			}
		}
		return oldCards;
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
