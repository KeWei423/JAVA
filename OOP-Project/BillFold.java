//-----------------1.4-----------------

public class BillFold {
	private Card card1;
    private Card card2;

    /**
     * Constructs a BillFold object with empty string name.
     */
	 public BillFold()
	 {
		card1=card2 = null ;
	 }
	 
	 
	 /**
	  * Mutator method to add a card to the BillFold
	  * @param card: the card to add
	  */
	  public void addCard(Card card)
	  {
		  if (card != null) {
			  if(card1 == null){
				  card1=card;
			  }
			  else if(card2==null){
				  card2 = card;
			  }
		  }
	  }
	  
	  
	  /**
	   * Gives a String format for all the cards in the BillFold
	   * @return the formatted String representing the cards
	   */
	  public String format()
	  {
		  String result = "";
		  if(card1!=null && card2!=null){
			  result = "["+card1.format()+"|"+card2.format()+"]";
		  }
		  else if(card1 == null && card2!=null){
			  result= "[EMPTY|"+card2.format()+"]";
		  }
		  else if(card1!=null && card2 == null){
			  result= "["+card1.format()+"|EMPTY]";
		  }
		  else{
			  result = "[EMPTY | EMPTY]";
		  }
		  
		  return result;
	  }
	  
	//-----------------1.9-----------------
	  /**
	   * counts the number of expired cards in the wallet
	   * @return the number of expired cards
	   */
	public int getExiredCardCount() {
		int counter = 0;
		if(card1!=null && card1.isExpired()){
			counter++;
		}
		if(card2!=null && card2.isExpired()){
			counter++;
		}
		return counter;
	}

}
