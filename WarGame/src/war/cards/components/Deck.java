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



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Deck represents a standard 52-card deck ofcards (without jokers).
 * 
 */

//This version of Deck fixed an error where the deck would report having no cards left, despite still having one card
//left to deal. It also has an improved toString() method.
public class Deck {
    
    /** Holds the cards in the deck. */
    private ArrayList<Card> cards;
    
    /** The index of the next card to deal. */
    private int nextCardIndex = 0;
    
    /** The randomizer instance. */
    private Random random = new Random();

    /**
     * Constructor.
     * 
     * Creates as a full, ORDERED deck. Don't forget to first Shuffle the deck!!
     */
    public Deck() {
        cards = new ArrayList<Card>(52); //create a new array of 52 cards

        for (int suit = Card.NO_OF_SUITS - 1; suit >= 0; suit--) //for each suit 
        {
            for (int rank = Card.NO_OF_RANKS - 1; rank >= 0 ; rank--) //for each rank 
            {
                cards.add(new Card(rank, suit)); //create a new card with that rank and suit
            }
        }
    }
    
    /**
     * Creates a deck of a certain set of cards.
     */
    
    public Deck(List<Card> theCards)
    {
    	cards = new ArrayList<Card>(theCards.size());
    	for (Card c : theCards)
    	{
    		cards.add(c);
    	}
    }
    
    /**
     * Gives you a half of the deck for the player to use
     */
    
    public List<Card> split(int start, int end)
    {
        if (end < start) 
            throw new IllegalArgumentException("end must be equal or greater than start");
    	ArrayList<Card> tempList = new ArrayList<Card>(end - start + 1);
		for (int i = start; i < end; i++)
		{
			tempList.add(cards.get(i));
		}
		return tempList;
    }
    
    /**
     * Adds a card to the bottom of your deck.
     */
    
    public void add(Card c)
    {
    	cards.add(c);
    }
    
    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        for (int oldIndex = 0; oldIndex < cards.size(); oldIndex++) 
        {
            int newIndex = random.nextInt(cards.size());
            Card tempCard = cards.get(oldIndex);
            cards.set(oldIndex, cards.get(newIndex));
            cards.set(newIndex, tempCard);
        }
        nextCardIndex = 0; //ensure we start back at the first card in the deck
    }
    
    /**
     * Resets the deck.
     * 
     * Does not re-order the cards.
     */
    public void reset() //i'm not sure when this would ever be used in the actual program. 
    {
        nextCardIndex = 0;
    }
    
    
    /**
     * Deals a single card.
     *
     * @return  the card dealt
     */
    public Card deal() {
        if (nextCardIndex + 1 > cards.size()) //used to say (nextCardIndex + 1 >= NO_OF_CARDS)
        {
            throw new IllegalStateException("No cards left in deck");
        }
        return cards.get(nextCardIndex++);
    }
    
    /**
     * Deals multiple cards at once. For example, you can deal an entire deck of 52 cards by calling deal(52)
     * 
     * @param noOfCards
     *            The number of cards to deal.
     * 
     * @return The cards.
     * 
     * @throws IllegalArgumentException
     *             If the number of cards is invalid.
     * @throws IllegalStateException
     *             If there are no cards left in the deck.
     */
    public ArrayList<Card> deal(int noOfCards) {
        if (cards.size() < 1) {
            throw new IllegalArgumentException("noOfCards < 1");
        }
        if (nextCardIndex + noOfCards > cards.size())
        {
            throw new IllegalStateException("No cards left in deck");
        }
        ArrayList<Card> dealtCards = new ArrayList<Card>();
        for (int i = 0; i < noOfCards; i++) {
            dealtCards.add(cards.get(nextCardIndex++));
        }
        return dealtCards;
    }
    
    /**
     * Deals a specific card.
     * 
     * @param rank
     *            The card's rank.
     * @param suit
     *            The card's suit.
     * 
     * @return The card if available, otherwise null.
     * 
     * @throws IllegalStateException
     *             If there are no cards left in the deck.
     */
    
 /*   //For Cheating? I'm not sure why we need this
    public Card deal(int rank, int suit) {
        if (nextCardIndex + 1 > NO_OF_CARDS) //used to say (nextCardIndex + 1 >= NO_OF_CARDS)
        {
            throw new IllegalStateException("No cards left in deck");
        }
        Card card = null;
        int index = -1;
        for (int i = nextCardIndex; i < NO_OF_CARDS; i++) {
            if ((cards[i].getRank() == rank) && (cards[i].getSuit() == suit)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            if (index != nextCardIndex) {
                Card nextCard = cards[nextCardIndex];
                cards[nextCardIndex] = cards[index];
                cards[index] = nextCard;
            }
            card = deal();
        }
        return card;
    }*/
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
    	String s = "";
        for (Card card : cards)
            s += card + "\n";
        return s;
    }
    
}
