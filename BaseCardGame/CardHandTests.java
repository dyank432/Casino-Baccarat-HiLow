import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardHandTests {

	@Test
	public void CardHandAddTest()
	{
	CardHand cardHand = new CardHand();
	Card jackOfDiamondsCard = new Card(11,0);
	Card queenOfHeartsCard = new Card(12,1);
	Card kingOfSpadesCard = new Card(13,2);
	
	String jackOfDiamonds = "Jack of Diamonds";
	String queenOfHearts = "Queen of Hearts";
	String kingOfSpades = "King of Spades";
	
	cardHand.addCard(jackOfDiamondsCard);
	cardHand.addCard(queenOfHeartsCard);
	cardHand.addCard(kingOfSpadesCard);
	
			
	assertEquals("hand matches expected hand content", jackOfDiamonds, cardHand.get(0).toString());
	assertEquals("hand matches expected hand content", queenOfHearts, cardHand.get(1).toString());
	assertEquals("hand matches expected hand content", kingOfSpades, cardHand.get(2).toString());
	assertEquals("hand matches expected hand content", "Hand (3) [cards=  0[ Jack of Diamonds]  1[  Queen of Hearts]  2[   King of Spades]]", cardHand.toString());

	}

}
