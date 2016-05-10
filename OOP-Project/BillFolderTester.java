
public class BillFolderTester {

public static void main(String[] args) {
	/*
	 * Basic setups test:
	 */
	Card card = new Card() ;
	System.out.println("Card format after default constructor:\n"  + card.format()) ;
	card = new Card("White Horse") ;
	System.out.println("Card format after default constructor:\n" + card.format()) ;
	
	Card CarD = new Card() ;
	System.out.println("Card format after default constructor:\n" + CarD.format()) ;
	CarD = new Card("Red Fox") ;
	System.out.println("Card format after constructor is given a name:\n" + CarD.format()) ;
	CarD = new CallingCard() ;
	System.out.println("CallingCard format after default constructor:\n" + CarD.format()) ;
	CarD = new CallingCard("Gray Bunny", "999", "abcd1234") ;
	System.out.println("CallingCard format, constructor was given name:\n" + CarD.format()) ;
	
	
	/*
	 * 1.5 using the BillFold
	 */
	BillFold billfold = new BillFold() ;
	System.out.println("Billfold before adding any cards: \n" + billfold.format()) ;
	
	billfold.addCard(new CallingCard("White Horse", "555", "Wh33Ho22")) ;
	System.out.println("Billfold after adding one card: \n" + billfold.format()) ;

	billfold.addCard(new Card("Red Fox")) ;
	System.out.println("Billfold after adding second card: \n" + billfold.format()) ;
	
	billfold.addCard(new CallingCard("Gray Bunny", "1234", "bb1234")) ;
	System.out.println("Billfold after adding third card: "+ billfold.format()) ;

	/*
	 * 1.10 using the isExpired Method
	 */ 
	System.out.println("-----------------------------") ;
	System.out.println("Number expired cards in Billfold after adding: ") ;
	BillFold Billfold = new BillFold() ;
	System.out.println("no cards: "+ Billfold.getExiredCardCount()) ;
	Billfold.addCard(new DriverLicense("Horse", 2006)) ;
	System.out.println("one card (expiring 2006): "+ Billfold.getExiredCardCount()) ;
	Billfold.addCard(new DriverLicense("Fox", 2004)) ;
	System.out.println("one more card (expiring 2004): " + Billfold.getExiredCardCount()) ;
	System.out.println("-----------------------------") ;
	System.out.println("Number expired cards in Billfold after adding: ") ;
	Billfold = new BillFold() ;
	System.out.println("no cards: "+ Billfold.getExiredCardCount()) ;
	Billfold.addCard(new CallingCard("Bunny", "012345", "kkk123")) ;
	System.out.println("one phone card: "+ Billfold.getExiredCardCount()) ;
	Billfold.addCard(new DriverLicense("Cat", 2004)) ;
	System.out.println("one more card (expiring 2004): " + Billfold.getExiredCardCount()) ;
	
	/*
	 * 1.11 using the toString Method
	 */ 
	System.out.println("-----------------------------") ;
	Card Card = new Card("Horse") ;
	System.out.println(Card) ;

	Card = new DriverLicense("Bunny", 2003) ;
	System.out.println(Card) ;

	Card = new CallingCard("Fox", "43218888", "eeedddmmm") ;
	System.out.println(Card) ;

	Card = new IDCard("Duck", "0123456789") ;
	System.out.println(Card) ;
	
	Card card1, card2, card3 ;
	/*
	 * 1.12 using the equals method on Card objects.
 	 */
	card1 = new Card("Horse") ;
	card2 = new Card("Fox") ;
	card3 = new Card("Bunny") ;
	test(card1, card2, card3) ;
	
	//ID card
	card1 = new IDCard("Horse", "kkk") ;
	card2 = new IDCard("Horse", "kkk") ;
	card3 = new IDCard("Horse", "klk") ;
	test(card1, card2, card3) ;
	
	//Calling Card
	card1 = new CallingCard("Horse", "myID", "myPIN") ;
	card2 = new CallingCard("Horse", "myID", "myPIN") ;
	card3 = null ;
	test(card1, card2, card3) ;
	
	//Driver License
	card1 = new DriverLicense("Horse", 2006) ;
	card2 = new DriverLicense("Horse", 2006) ;
	card3 = new DriverLicense("Horse", 2005) ;
	test(card1, card2, card3) ;
	
	}

	/**
	 * 1.12: using the equals method on three card objects.
	 *       Try to make card 1 and card2 the same and different from card 3.
	 * @param card1: first card
	 * @param card2: second card
	 * @param card3: third card
	 */
	public static void test(Card card1, Card card2, Card card3)
	{
		System.out.println("-----------------------------") ;
		System.out.println("Card 1: " + card1) ;
		System.out.println("Card 2: " + card2) ;
		System.out.println("Card 3: " + card3) ;
		System.out.println("-----------------------------") ;
		if (card1.equals(card2)){
		    System.out.println("Cards 1 and 2 are same as expected") ;
		}
		else {
		    System.out.println("Surprisingly, cards 1 and 2 are not equal.") ;
		}
		
		if (card1.equals(card3)){
		    System.out.println("Suprisingly cards 1 and 3 equal") ;
		}
		else {
		    System.out.println("Cards 1 and 3 are not equal, as expected.") ;
		}
	}

}
