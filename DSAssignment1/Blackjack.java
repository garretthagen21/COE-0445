import java.util.*;

//import Card.Ranks;

public class Blackjack{
	
	static boolean traceOn=true;
	
	public static void main(String args[]) {
		int numRounds = Integer.parseInt(args[0]);
		int numDecks = Integer.parseInt(args[1]);
		//int numRounds = 1000;
		//int numDecks = 8;
		
		if(numRounds>10) {
			traceOn=false;
		}
		
		int initialCardAmount=numDecks*52;
		int dealerWins=0,playerWins=0,numPushes=0;
		int playerTotal=0,dealerTotal=0;
		boolean playerBusted,dealerBusted;
		String roundWinner;
		
		RandIndexQueue<Card> playerCards = new RandIndexQueue<Card>(5);
		RandIndexQueue<Card> dealerCards = new RandIndexQueue<Card>(5);
		RandIndexQueue<Card> allCards = new RandIndexQueue<Card>(initialCardAmount);
		RandIndexQueue<Card> discardCards = new RandIndexQueue<Card>(initialCardAmount);
		
		
		System.out.println("Starting Blackjack with "+numRounds+ " rounds and "+numDecks+" decks in the shoe.");
		
		//Make the shoe
		for(int i=0;i<numDecks;i++) {
			
		
			for (Card.Suits s: Card.Suits.values()) {	//Cycle through suits
				for (Card.Ranks r: Card.Ranks.values()) {  	//Adds a card of every rank of the current suit
					Card c = new Card(s,r);
					allCards.offer(c);
				}
			}		
	     }
		allCards.shuffle();		//Shuffle the deck
		//System.out.println(allCards.toString());
		
		
		
		for (int roundCount=1;roundCount<=numRounds;roundCount++){			//Beginning of game
			if(traceOn)
				System.out.println("\nRound "+roundCount+" beginning.");		//Announce beginning of round;
			
			
			//Get player and dealer two cards each
			playerCards.offer(allCards.poll());
			playerCards.offer(allCards.poll());
			dealerCards.offer(allCards.poll());
			dealerCards.offer(allCards.poll());
			
			playerTotal=calculateTotal(playerCards,false);			//Calculate initial total
			dealerTotal=calculateTotal(dealerCards,false);
				
			//if (traceOn) {
			showCardContents(playerCards,playerTotal,"Player");		//Show player cards 
			showCardContents(dealerCards,dealerTotal,"Dealer");		//Show player cards 
			//}
			
			
			playerTotal=calculateMoves(playerCards,allCards,playerTotal,"Player"); //Calculate player moves
			playerBusted=didBust(playerTotal);		                              //See if player or dealer busted
			
			
			if(playerBusted) {
				dealerWins++;
				roundWinner="Dealer Wins!";
				
			}
			else {
				dealerTotal=calculateMoves(dealerCards,allCards,dealerTotal,"Dealer"); //Calculate dealer moves if the player did not bust
				dealerBusted=didBust(dealerTotal);
				if(dealerBusted) {
					playerWins++;
					roundWinner="Player Wins!";
				}
				else {
					if(dealerTotal>playerTotal) {					//If the dealer has more than player
						dealerWins++;
						roundWinner="Dealer Wins!";
					}
					else if(playerTotal>dealerTotal) {
						playerWins++;
						roundWinner="Player Wins!";
					}
					else {										//Tie
						numPushes++;
						roundWinner="Push!";
					}
				}
			}
			if(traceOn)
				System.out.println("Result: "+roundWinner);			//If the dealer busts
			
			emptyCards(playerCards,discardCards);			//Empty the player and dealer cards into the discard pile
			emptyCards(dealerCards,discardCards);
			
			
			
			
			if(allCards.size()<=(initialCardAmount/4)) {
				//add the discard deck back into the main deck
				System.out.println("\nReshuffling the shoe in round " +roundCount);
				emptyCards(discardCards,allCards);
				allCards.shuffle();
			}
		
			
	    }
		
	
			
			
			
			
			
		showResults(numRounds,playerWins,dealerWins,numPushes);	//When the game ends
			
	
		
		
		
		
		
		
	}
	public static void emptyCards(RandIndexQueue cardSource, RandIndexQueue cardDestination) {
		
		int sourceSize = cardSource.size();
		for(int i=0;i<sourceSize;i++) {
			cardDestination.offer(cardSource.poll());
		}
		
		
		
		
	}
	
	public static boolean didBust(int userTotal) {
		if(userTotal>21) {
			return true;
		}
		return false;
	}
	
	
	public static int calculateMoves(RandIndexQueue userCards, RandIndexQueue allCards,int userTotal,String userType) {
	
	boolean tryAgain=true;	//Should attempt again if ace is converted to 1 
	boolean lowerAce=false; //Ace is 11 unless we bust
	String result="";
	
	while(tryAgain) {
		while(userTotal<17) {
		userCards.offer(allCards.poll());
		if(traceOn)
			System.out.println(userType+" hits: "+userCards.peek());
		userTotal=calculateTotal(userCards,lowerAce);
	}
	
	if(userTotal>21) {
		lowerAce=true;
		//Use the 1 value for ace if player busts
		userTotal=calculateTotal(userCards,lowerAce);
		
		if(userTotal<17) {
		tryAgain=true;
		}
		else {
		tryAgain=false;
		result=" BUSTS";
		}
	}
	else if(userTotal==21) {
		result=" HITS BLACKJACK";
		tryAgain=false;
	}
	else {
		result=" STANDS";
		tryAgain=false;
	}
	
	
		showCardContents(userCards,userTotal,userType+result);
	
	
	}
	return userTotal;
	
}
	
	
	
	
	
	public static int calculateTotal(RandIndexQueue userCards,boolean lowerAce) {
	
		int total=0;
		
		if(lowerAce) {
			for(int i=0;i<userCards.size();i++) {
				
				total+=((Card)userCards.get(i)).value2();	//1 ace value
			}
			
		  }
		else {
			for(int i=0;i<userCards.size();i++) {
			    total+=((Card)userCards.get(i)).value();		//11 ace value
			}
		}
		return total;
	}
	
	public static void showCardContents(RandIndexQueue userCards, int userTotal,String userType) {
		if(traceOn)
			System.out.println(userType+": " + userCards.toString() + ": "+userTotal);	//Show user cards
		
	}
	
	
	public static void showResults(int rounds,int playerWins,int dealerWins,int pushes) {
		System.out.println();
		System.out.println("After "+rounds+" rounds here are the results:");			//Print the results at the end of the game
		System.out.println("\tDealer Wins: "+dealerWins);
		System.out.println("\tPlayer Wins: "+playerWins);
		System.out.println("\tPushes: "+pushes);
		
	}
	
	
}