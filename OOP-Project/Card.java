
public class Card {
	private String name;
	
	/**
	 * default ctor
	 */
	public Card(){
		name = "";
	}
	
	
	/**
	 * parameterized ctor
	 * @param n is the name of card
	 */
	public Card(String n){
		name = n;
	}
	
	
	/**
	 * Accessor of private variable
	 * @return name;
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * check if the card is expired
	 * @return by default it is false: not expired
	 */
	public boolean isExpired(){
		return false;
	}
	
	
	/**
	 * format the Card to give card holder's name
	 * @return " holder's name
	 */
	public String format(){
		return "Card holder:" + name;
	}
	
	//-----------------1.11-----------------
	/**
	 * make the format to a string 
	 * @return the class's name + the Card holder's name
	 */
	public String toString(){
		return getClass().getName()+"[Name ="+name+"]";
	}
	
	//-----------------1.12-----------------
	/**
	 * check if the 2 class are the same instances of each other
	 * @return: true: same; false: different
	 */
	public boolean equals(Object other)
    {  
		if (other == null){
			return false;
		}
		if (! getClass().equals(other.getClass())) {
			return false ;
		}
		Card card = (Card) other ;
		return name.equals(card.name) ;
    }

}
