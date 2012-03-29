package war.cards.components;
// This file is a modified version of a file included in the 'texasholdem' 
// project, an open source Texas Hold'em poker application originally written 
// by Oscar Stigter (Copyright 2009). This modified version was created by the author
// listed below:
//
// Copyright 2012 Steven Dunn (University of California, Irvine)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.



import java.io.Serializable;

/**
 * A Card represents a single playing card in a standard 52 card deck (without jokers).
 * 
 * Its value is determined by it's numeric rank. Suits are needed to determine if a player has 
 * a Flush or Straight Flush, but Suits do NOT effect the value of a hand in Texas Hold 'em 
 * (i.e. one suit is not more powerful than another suit).
 * 
 * For example, If one person has a 10, J, Q, K, A of Hearts (a Straight flush), this hand
 * is equivalent to a person with a 10, J, Q, K, A of Spades. The players would split the pot.
 * 
 * @author Steven Dunn
 */

public class Card implements Comparable<Card>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7310532373728214006L;

	/** The number of ranks in a deck. */
	public static final int NO_OF_RANKS = 13;

	/** The number of suits in a deck. */
	public static final int NO_OF_SUITS = 4;

	// The ranks. These are labeled starting from zero, to make array searching feel more natural.
	public static final int ACE      = 12;
	public static final int KING     = 11;
	public static final int QUEEN    = 10;
	public static final int JACK     = 9;
	public static final int TEN      = 8;
	public static final int NINE     = 7;
	public static final int EIGHT    = 6;
	public static final int SEVEN    = 5;
	public static final int SIX      = 4;
	public static final int FIVE     = 3;
	public static final int FOUR     = 2;
	public static final int THREE    = 1;
	public static final int DEUCE    = 0;

	// The suits.
	public static final int SPADES   = 3;
	public static final int HEARTS   = 2;
	public static final int CLUBS    = 1;
	public static final int DIAMONDS = 0;

	/** The rank symbols. */
	//Rank symbols are used when constructing a card using the alternative constructor using a string (see below). 
	//For example, if you want to create a King of Hearts, you would write new Card("Kh")...NOT new Card("13h").
	
	public static final String[] RANK_SYMBOLS = 
	{
		"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"
	};

	/** The suit symbols. */
	public static final char[] SUIT_SYMBOLS = { 'd', 'c', 'h', 's' };  //diamonds, clubs, hearts, spades

	/** The rank. */
	private final int rank;

	/** The suit. */
	private final int suit;

	/**
	 * Constructor based on rank and suit.
	 * 
	 * @param rank
	 *            The rank.
	 * @param suit
	 *            The suit.
	 * 
	 * @throws IllegalArgumentException
	 *             If the rank or suit is invalid.
	 */
	public Card(int rank, int suit) {
		if (rank < 0 || rank > NO_OF_RANKS - 1) {
			throw new IllegalArgumentException("Invalid rank");
		}
		if (suit < 0 || suit > NO_OF_SUITS - 1) {
			throw new IllegalArgumentException("Invalid suit");
		}
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * Alternative Card Constructor is passed a String, that must consist of 2 characters: 
	 * 
	 * The rank character and a suit character, in that order.
	 * 
	 * @param s
	 *            The string representation of the card, e.g. "As", "Td", "7h".
	 * 
	 * @return The card.
	 * 
	 * @throws IllegalArgumentException
	 *             If the card string is null or of invalid length, or the rank
	 *             or suit could not be parsed.
	 */
	public Card(String s) {
		if (s == null) {
			throw new IllegalArgumentException("Null string or of invalid length");
		}
		s = s.trim();
		if (s.length() != 2) {
			throw new IllegalArgumentException("Empty string or invalid length");
		}

		//Parse the rank character.
		String rankSymbol = s.substring(0, 1); //get the rank
		char suitSymbol = s.charAt(1);
		int rank = -1;
		//Check that the rank passed is one of the valid ranks
		for (int i = 0; i < Card.NO_OF_RANKS; i++) 
		{
			if (rankSymbol.equals(RANK_SYMBOLS[i])) 
			{
				rank = i;
				break;
			}
		}
		if (rank == -1) {
			throw new IllegalArgumentException("Unknown rank: " + rankSymbol);
		}
		//Parse the suit character.
		int suit = -1;
		//Check that the suit passed is one of the valid suits
		for (int i = 0; i < Card.NO_OF_SUITS; i++) {
			if (suitSymbol == SUIT_SYMBOLS[i]) {
				suit = i;
				break;
			}
		}
		if (suit == -1) {
			throw new IllegalArgumentException("Unknown suit: " + suitSymbol);
		}
		
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * Returns the suit.
	 * 
	 * @return The suit.
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * Returns the rank in String form.
	 * 
	 * @return The rank.
	 * @throws Exception 
	 */
	public String getRankString() throws Exception {
		return toStringRankHelper(rank);
	}
	
	/**
	 * Returns the suit in String form.
	 * 
	 * @return The suit.
	 * @throws Exception 
	 */
	public String getSuitString() throws Exception {
		return toStringSuitHelper(suit);
	}

	/**
	 * Returns the rank.
	 * 
	 * @return The rank.
	 */
	public int getRank() {
		return rank;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (rank * NO_OF_SUITS + suit);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	//If the cards are the EXACT same card (Suit and Rank), then they are the same card
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			return ((Card) obj).hashCode() == hashCode();
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Card card) {
		int thisValue = getRank();
		int otherValue = card.getRank();
		if (thisValue < otherValue) {
			return -1;
		} else if (thisValue > otherValue) {
			return 1;
		} else {
			return 0;
		}
	}

	//Helps format the toString to make it more meaningful
	private String toStringSuitHelper(int suit) throws Exception
	{ 
		if (suit == Card.SPADES)
		return "s";
		if (suit == Card.CLUBS)
			return "c";
		if (suit == Card.HEARTS)
			return "h";
		if (suit == Card.DIAMONDS)
			return "d";
		else
			throw new Exception("Error");
		
	}
	
	//Helps format the toString to make it more meaningful
	private String toStringRankHelper(int rank) throws Exception
	{ 
		if (rank == Card.ACE)
			return "1";
		if (rank == Card.KING)
			return "k";
		if (rank == Card.QUEEN)
			return "q";
		if (rank == Card.JACK)
			return "j";
		if (rank == Card.TEN)
			return "10";
		if (rank == Card.NINE)
			return "9";
		if (rank == Card.EIGHT)
			return "8";
		if (rank == Card.SEVEN)
			return "7";
		if (rank == Card.SIX)
			return "6";
		if (rank == Card.FIVE)
			return "5";
		if (rank == Card.FOUR)
			return "4";
		if (rank == Card.THREE)
			return "3";
		if (rank == Card.DEUCE)
			return "2";
		else
			throw new Exception("error");
	}
		
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			return toStringSuitHelper(suit) + toStringRankHelper(rank);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
