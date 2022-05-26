import java.util.Scanner;
 
public class PuntoBanco 
{

	private Deck gameDeck;
	private double coins;
	private Player player;
	
	/**
	 * Default constructor 
	 * @param player - gets the player info from the Player class (name and coins) 
	 */
	public PuntoBanco(Player player)
	{
		
	this.player = player;	
	Deck gameDeck = new Deck();
	gameDeck.shuffle();

	this.gameDeck = gameDeck;
	this.coins = player.getCoins();
	
	System.out.println("\n\nPUNTOBANCO");
	System.out.println("----------");
	
	play();
	}
	
	/**
	* play()
	* plays one round of Punto Banco, then asks if the player wants to quit or play again.
	*/
	public void play()
	{
		Card pCard1;
		Card pCard2;
		Card pCard3;
		Card bCard1;
		Card bCard2;
		Card bCard3;

		
		char choice;
		boolean natural = false;
		int bankerR1Total;
		int playerR1Total;
		int playerDrawTotal = 0;
		int bankerDrawTotal = 0;
		int playerFinal = 0;
		int bankerFinal = 0;
				
		Scanner keyboard = new Scanner(System.in);
		System.out.println("current coins: " + coins);
		System.out.print("Bet amount: ");
		double bet = keyboard.nextDouble();
	
		if (bet <= coins)
		{
		
			choice = betChoice();
			choice = Character.toUpperCase(choice);
			System.out.println("you bet on " + choice);
					
			pCard1 = gameDeck.dealCard();
			System.out.println("Player was dealt " + pCard1.toString());
			bCard1 = gameDeck.dealCard();
			System.out.println("Banker was dealt " + bCard1.toString());
			pCard2 = gameDeck.dealCard();
			System.out.println("Player was dealt " + pCard2.toString());
			bCard2 = gameDeck.dealCard();
			System.out.println("Banker was dealt " + bCard2.toString() + "\n");
			
			playerR1Total = calculateTotal(pCard1,pCard2);
			bankerR1Total = calculateTotal(bCard1,bCard2);
			
			System.out.println("Player shows " + playerR1Total);
			System.out.println("Banker has " + bankerR1Total + "\n");
			
			if ((bankerR1Total == 8 || bankerR1Total == 9) || (playerR1Total == 8 || playerR1Total == 9)) //natural 8,9
			{
				natural = true;
			}
			
			if (!natural)
			{	
				if (playerDraw(playerR1Total)) //player 0-5
				{
					pCard3 = gameDeck.dealCard();
					System.out.println("Player was dealt " + pCard3.toString());
					
					playerDrawTotal = calcCard(pCard3);
					
					if (bankerDraw(playerDrawTotal,bankerR1Total)) //player 0-5 && banker draws
					{
						bCard3 = gameDeck.dealCard();
						System.out.println("Banker was dealt " + bCard3.toString());					
						bankerDrawTotal = calcCard(bCard3);
					}
					
				}
				else //banker 0-5, player no draw
				{
					if (playerDraw(bankerR1Total))
					{
						bCard3 = gameDeck.dealCard();
						System.out.println("Banker was dealt " + bCard3.toString());
						bankerDrawTotal = calcCard(bCard3);
					}
				}
			}
			
			System.out.println("----------------------------------------");			
			playerFinal = (playerR1Total + playerDrawTotal) % 10;
			bankerFinal = (bankerR1Total + bankerDrawTotal) % 10;		

			if (playerFinal > bankerFinal)
			{
				System.out.println("Player wins " + playerFinal + " over " + bankerFinal);
				if (choice == 'P')
				{
					coins += bet;
					System.out.println("you won " + bet + "\n");
				}
				else
				{
					coins -= bet;
					System.out.println("you lost " + bet + "\n");
				}
			}
			else if (playerFinal < bankerFinal)
			{
				System.out.println("Banker wins " + bankerFinal + " over " + playerFinal);
				if (choice == 'B')
				{
					coins += (bet*0.95);
					System.out.println("you won " + (bet*0.95) + "\n");
				}
				else
				{
					coins -= bet;
					System.out.println("you lost " + bet + "\n");
				}
			}
			else
			{
				System.out.println("" + bankerFinal + "-" + playerFinal + " tie");
				if (choice == 'T')
				{
					coins += (bet*8);
					System.out.println("you won " + (bet*8) + "\n");
				}
			}
		}
		
		System.out.println("play again (p) or quit to menu (q)"); // should happen at the end of every round.
		choice = keyboard.next().charAt(0);
		
		while ((choice != 'p' && choice != 'q') && (choice != 'P' && choice != 'Q'))
		{
			System.out.println("play again (p) or quit to menu (q)");
		    choice = keyboard.next().charAt(0);
		}
		if (choice == 'p' || choice == 'P')
		{
			play();
		}
		if (choice == 'q' || choice == 'Q')
		{
			player.updateCoins(coins);
	    	System.out.println("playerName: " + player.getPlayerName());
	    	System.out.println("coins: " + player.getCoins() + "\n");
		}
	}
	
	/**
	 * bankerDraw()
	 * @param playerDrawTotal - Third player card numeric value.
	 * @param bankerR1Total - The sum of the first two banker cards as a numeric value.
	 * determines whether banker should draw a third card.
	 */
	private boolean bankerDraw(int playerDrawTotal, int bankerR1Total) 
	{
		boolean draw = false;
		
		if ((playerDrawTotal == 2 || playerDrawTotal == 3) && bankerR1Total <= 4) //Player draw is 2-3
		{
			draw = true;
		}
		else if ((playerDrawTotal == 4 || playerDrawTotal == 5) && bankerR1Total <= 5) //Player draw is 4-5
		{
			draw = true;
		}
		else if ((playerDrawTotal == 6 || playerDrawTotal == 7) && bankerR1Total <= 6) //Player draw is 6-7
		{
			draw = true;
		}
		else if (playerDrawTotal == 8 && bankerR1Total <= 2) //Player draw is 8
		{
			draw = true;
		}
		else
		{
			if (bankerR1Total <= 3) //Player draw is 9,10,A
			{
				draw = true;
			}
		}
		
		return draw;
	}
	/**
	 * calcCard 
	 * @param card - the card to be calculated
	 * @return the game value for that card (face cards are 10 and ace is 1).
	 * Calculates the game value for one card.
	 */
	private int calcCard(Card card) 
	{
		int cardVal;
		
		if (card.getRank() >= 10 && card.getRank() <= 13)
		{
			cardVal = 10;
		}
		else if (card.getRank() == 14)
		{
			cardVal = 1;
		}
		else
		{
			cardVal = card.getRank();
		}
		
		return cardVal;
	}
	/**
	 * playerDraw()
	 * @param total - The sum of the first two cards as a numeric value.
	 * determines whether player should draw a third card.
	 */
	private boolean playerDraw(int total) 
	{
		boolean draw = false;
		
		if (0 <= total  && total <= 5)
		{
			draw = true;	
		}
		
		return draw;
	}
	/**
	 * calculateTotal
	 * @param card1 - the first card dealt.
	 * @param card2 - the second card dealt.
	 * @return the total numeric value of both cards added together mod 10
	 * adds both cards together, mod by 10 to calculate the game value for the cards.
	 */
	private int calculateTotal(Card card1, Card card2)
	{
		int card1Val;
		int card2Val;
		
		if (card1.getRank() >= 10 && card1.getRank() <= 13)
		{
			card1Val = 10;
		}
		else if (card1.getRank() == 14)
		{
			card1Val = 1;
		}
		else
		{
			card1Val = card1.getRank();
		}
		
		if (card2.getRank() >= 10 && card2.getRank() <= 13)
		{
			card2Val = 10;
		}
		else if (card2.getRank() == 14)
		{
			card2Val = 1;
		}
		else
		{
			card2Val = card2.getRank();
		}		
		
		 return (card1Val + card2Val) % 10; 
	}
	/**
	 * betChoice()
	 * validates that the user input is one of: P,B or T.
	 * @return choice - the validated input.
	 */
	private char betChoice()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("who will win next hand, player (P), banker (B) or tie (T) ");
		char choice = keyboard.next().charAt(0);
	
		while ((choice != 'p' && choice != 'b' && choice != 't' ) && (choice != 'P' && choice != 'B' && choice != 'T'))
		{
			System.out.print("who will win next hand, player (P), banker (B) or tie (T) ");
			choice = keyboard.next().charAt(0);
		}
		
		return choice;
	}
}