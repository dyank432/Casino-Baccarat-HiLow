/**
 * Main used to run the program for testing the basic required features
 * @author jkidney
 * @version March 11, 2013
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader; 
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File; 
import java.util.Scanner;

public class Runner 
{
	private Menu deckMenu;
	private boolean endProgram;
	private Deck gameDeck;
	private ConsoleComunicationController comm;
	private Player player;
	/**
	 * Default constructor 
	 */
	public Runner() throws FileNotFoundException
	{
		comm = new ConsoleComunicationController();
		endProgram = false;
		gameDeck = new Deck();
		
		try
	    {
	    Scanner fileReader = new Scanner (new File("CasinoInfo.txt"));
	    
	    while (fileReader.hasNext())
	    {
	    	String playerName = fileReader.nextLine();
	    	double coins = fileReader.nextDouble();
	    	
	    	Player playr = new Player(playerName,coins);
	    	this.player = playr;
	    }
	    
	    fileReader.close();	
	    	
	    }
	    catch (FileNotFoundException exception)
	    {
	    	Scanner keyboard = new Scanner(System.in);
	    	System.out.print("enter player name: ");
	    	String playerName = keyboard.next().trim();
	    	Player player = new Player(playerName,1000);
	    }
		
		setUpMenu();
	}
	
	/**
	 * Sets up the menu that will be used just for part one of the program
	 */
	private void setUpMenu()
	{		
		deckMenu = new Menu(comm);

		deckMenu.addMenuOption( new MenuOption('H',"Play HiLow game") );
		deckMenu.addMenuOption( new MenuOption('P',"Play Punto Banco game") );
		
		deckMenu.addMenuOption( new MenuOption('N',"Create New deck") );
		deckMenu.addMenuOption( new MenuOption('S',"Shuffle the deck") );
		deckMenu.addMenuOption( new MenuOption('T',"Show the top card") );
		deckMenu.addMenuOption( new MenuOption('C',"Cut the deck") );
		deckMenu.addMenuOption( new MenuOption('D',"Deal the hands") );
		deckMenu.addMenuOption( new MenuOption('R',"Print deck") );
		deckMenu.addMenuOption( new MenuOption('Q',"Quit") );
	}

	/**
	 * runs the selected user choice
	 * @param userSelection the validated selection given by the user
	 */
	private void runSelection(char userSelection) throws FileNotFoundException
	{
		int numCards = 0;
		CardHand hand1=null, hand2=null;
		
		switch(userSelection)
		{
		case 'H': HiLow hiLow = new HiLow(player); break;
		case 'P': PuntoBanco puntobanco = new PuntoBanco(player); break;
		
		case 'N': gameDeck = new Deck(); break;
		case 'S': 
			System.out.println("Before Shuffle");
			System.out.println(gameDeck);

			gameDeck.shuffle();

			System.out.println("After Shuffle");
			System.out.println(gameDeck);


			break;
		case 'T': System.out.println("Top Card = " + gameDeck.topCard() ); break;
		case 'C': 
			System.out.println("Before Cut");
			System.out.println(gameDeck);

			System.out.println("Cut Card = " + gameDeck.cut() );
			
			System.out.println("After Cut");
			System.out.println(gameDeck);

			break;
		case 'D':
			 numCards = comm.getInput_Int("Enter the number of cards you want in the hands: ");
			 
		     hand1 = gameDeck.dealHand(numCards);
		     hand2 = gameDeck.dealHand(numCards);
			
		     System.out.println("Hand1="+hand1);
		     System.out.println("Hand2="+hand2);
		     
			break;
		case 'R': System.out.println(gameDeck); break;
		
		case 'Q': 			
		updateFile();
		endProgram = true;
		break;
		
		}
	}

	/**
	 * Main Startup method for the part one of the program. It will
	 * run the entire interaction with the user.
	 */
	public void run() throws FileNotFoundException
	{		
		char selection = ' ';
		
		while(!endProgram)
		{
			selection = deckMenu.getUserChoice();
			runSelection(selection);
		}
	}
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		Runner main = new Runner();
		main.run();
	}

	/**
	 * updateFile
	 * saves the new value for coins into the "CasinoInfo.txt" file 
	*/
	private void updateFile() throws FileNotFoundException
	{
		try 
		{	
	    	System.out.println("playerName: " + player.getPlayerName());
	    	System.out.println("UpdatedCoins: " + player.getCoins());
			
			FileWriter fw = new FileWriter("CasinoInfo.txt",false);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(player.getPlayerName());
			pw.println(player.getCoins());
	
			pw.close();
			fw.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
