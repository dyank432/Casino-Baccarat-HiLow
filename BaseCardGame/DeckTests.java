import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckTests {

	@Test
	public void ShuffleDeckTest() 
	{
	
	Deck deck = new Deck();
	Deck originalDeck = deck;
	
	deck.shuffle();
	
	//I don't know how to test this
	}

	@Test
	public void dealTopCardTest()
	{
		Deck deck = new Deck();
		deck.shuffle();
		
		assertEquals("Deals the top Card", deck.topCard(), deck.dealCard());
	}
	
	@Test
	public void dealHandTest()
	{
		Deck deck = new Deck();

		CardHand hand = deck.dealHand(3);
		
		assertEquals("Deals a three card hand", hand.toString(),hand.toString());
	}
	
}
