import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;

class CardTests 
	{
	
		@Test
		public void CardGettersTest()
		{
		Card card = new Card(2,0);
		
		assertEquals("2 of diamond returns rank of 2", 2, card.getRank());
		assertEquals("2 of diamonds returns suit of Diamonds", 0, card.getSuit());
		}
		
		@Test
		public void CardSuitAndFaceTest()
		{
		Card jackOfDiamondsCard = new Card(11,0);
		Card queenOfHeartsCard = new Card(12,1);
		Card kingOfSpadesCard = new Card(13,2);
		Card aceOfClubsCard = new Card(14,3);

		String jackOfDiamonds = "Jack of Diamonds";
		String queenOfHearts = "Queen of Hearts";
		String kingOfSpades = "King of Spades";
		String aceOfClubs = "Ace of Clubs";
		
		assertEquals("new card: Jack of Diamonds", jackOfDiamonds, jackOfDiamondsCard.toString());
		assertEquals("new card: Queen of Hearts", queenOfHearts, queenOfHeartsCard.toString());
		assertEquals("new card: King of Spades", kingOfSpades, kingOfSpadesCard.toString());
		assertEquals("new card: Ace of Clubs", aceOfClubs, aceOfClubsCard.toString());
		}
	}
