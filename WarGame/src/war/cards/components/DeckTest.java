package war.cards.components;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

public class DeckTest {

	@Test
	public void War() {
		ArrayList<Card> aList = new ArrayList<Card>(5);
		aList.add(new Card("As"));
		aList.add(new Card("2s"));
		aList.add(new Card("3s"));
		aList.add(new Card("4s"));
		aList.add(new Card("5s"));
		ArrayList<Card> bList = new ArrayList<Card>(5);
		bList.add(new Card("Ah"));
		bList.add(new Card("2h"));
		bList.add(new Card("3h"));
		bList.add(new Card("4h"));
		bList.add(new Card("6h"));
		Deck deckA = new Deck(aList);
		Deck deckB = new Deck(bList);
		Card card1 = deckA.deal();
		Card card2 = deckB.deal();
		Assert.assertEquals(card1.compareTo(card2), 0);
		deckA.deal(3);
		deckB.deal(3);
		card1 = deckA.deal();
		card2 = deckB.deal();
		Assert.assertEquals(card1.compareTo(card2), -1);
	}
	
	@Test
	public void War2() {
		ArrayList<Card> aList = new ArrayList<Card>(5);
		aList.add(new Card("As"));
		aList.add(new Card("2s"));
		aList.add(new Card("5s"));
		ArrayList<Card> bList = new ArrayList<Card>(5);
		bList.add(new Card("3h"));
		bList.add(new Card("4h"));
		bList.add(new Card("6h"));
		Deck deckA = new Deck(aList);
		Deck deckB = new Deck(bList);
		deckA.deal();
		deckB.deal();
		deckA.add(deckB.remove());
		deckA.deal();
		deckB.deal();
		deckB.add(deckA.remove());
		deckA.deal();
		deckB.deal();
		deckB.add(deckA.remove());
		Card card1 = deckA.deal();
		Card card2 = deckB.deal();
		Assert.assertEquals(card1.toString(), "h3");
		Assert.assertEquals(card2.toString(), "s2");
		card1 = deckA.deal();
		Assert.assertEquals(card1.toString(), "s1");
	}

}
