import java.util.Scanner;

public class HiLow {
	
	private Player player;
	private Deck bankerDeck;
	private Deck playerDeck;
	private double coins;
	
	/**
	* default constructor
	* 	 * @param player - gets the player info from the Player class (name and coins) 
	*/
	
	public HiLow(Player player) // primary constructor
	{

		this.player = player;
		
		Deck bankerDeck = new Deck();
		bankerDeck.shuffle();
		Deck playerDeck = new Deck();
		playerDeck.shuffle();

		this.bankerDeck = bankerDeck;
		this.playerDeck = playerDeck;
		
		this.coins = player.getCoins();
		
		System.out.println("\n\nHILOW");
		System.out.println("----------");
		play();
	}
	
	/**
	* play()
	* plays one round of HiLow, then asks if the player wants to quit or play again.
	*/
	public void play()
	{
		Card card1;
		Card card2;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("current coins: " + coins);
		System.out.print("Bet amount: ");
		double bet = keyboard.nextDouble();
		
		if (bet <= coins)
		{			
			card1 = playerDeck.dealCard();

			System.out.println("you were dealt " + card1.toString());
			System.out.println("will the next card be Higher, Lower, or the Same?");
			String guess = keyboard.next().trim();
			while (!validGuess(guess))
			{
				System.out.println("will the next card be Higher, Lower, or the Same?");
			    guess = keyboard.next().trim();				
			}
			guess = guess.toLowerCase();

			card2 = bankerDeck.dealCard();
			System.out.println("next card was " + card2.toString());

			switch(guess)
			{
				case "higher":
					if (card2.getRank() > card1.getRank())
					{
						winMessage();
						coins += bet;
					}
					else
					{
						lossMessage();
						coins -= bet;
					}
					break;
					
				case "same":
					if (card1.getRank() == card2.getRank() && card1.getSuit() != card2.getSuit())
					{
						winMessage();
						coins += (bet*2);
					}
			
					else if (card1.equals(card2))
					{
						System.out.println("you tied.");
					}
					else
					{
						lossMessage();
						coins -= bet;
					}
					break;
					
				case "lower":
					if (card2.getRank() < card1.getRank())
					{
						winMessage();
						coins += bet;
					}
					else
					{
						lossMessage();
						coins -= bet;
					}
					break;
			}
	
			 if (playerDeck.size() == 0)
			 {
					Deck bankerDeck = new Deck();
					bankerDeck.shuffle();
					Deck playerDeck = new Deck();
					playerDeck.shuffle();
					
					System.out.println("shuffling new deck");
			 }
			
		}
		System.out.println("play again (p) or quit to menu (q)"); // should happen at the end of every round.
		char choice = keyboard.next().charAt(0);
		
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
	* winMessage()
	* displays a win message.
	*/
	private void winMessage()
	{
		System.out.println("you won.");
	}
	/**
	* loseMessage()
	* displays a loss message.
	*/	
	private void lossMessage()
	{
		System.out.println("you lost.");
	}
	/**
	* validGuess()
	* @param guess - the guess from the user to be validated.
	* @return valid - valid when user input is correct.
	* checks whether the user input is one of HIGHER, LOWER, or SAME.
	*/		
	private boolean validGuess(String guess)
	{
		boolean valid = false;
		
		if (guess.equalsIgnoreCase("HIGHER") || guess.equalsIgnoreCase("LOWER") || guess.equalsIgnoreCase("SAME"))
		{
			valid = true;
		}
		
		return valid;
	}
}
