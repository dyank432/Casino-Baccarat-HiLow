
public class Player {

	private String playerName;
	private double coins;
	
	/**
	* Constructor
	* @param playerName - the player's name.
	* 	      	  coins - the amount of coins available.
	* Sets playerName and coins.
	*/
	public Player(String playerName, double coins)
	{
		this.playerName = playerName;
		this.coins = coins;
	}
	/**
	* default Constructor
	*/ 	
	public Player() 
	{
		this.playerName = "John";
		this.coins = 1000;
	}
	/**
	* updateCoins
	* @param newCoins - the new coin value to update to.
	* sets the coin value to the new value.
	*/ 
	public void updateCoins(double newCoins)
	{
		this.coins = newCoins;
	}
	/**
	* getCoins
	* getter for coins
	*/ 	
	public double getCoins()
	{
		return coins;
	}
	/**
	* getPlayerName
	* getter for playerName
	*/ 		
	public String getPlayerName()
	{
		return playerName;
	}
}
