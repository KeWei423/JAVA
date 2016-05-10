
public class CallingCard extends Card{
	//-----------------1.1-----------------
	private String CardNumb;
	private String PIN;
	
	/**
	 * default ctor
	 */
	public CallingCard(){
		super();
		CardNumb = "";
		PIN = "";
	}
	
	//-----------------1.2-----------------
	/**
	 * Constructs a CallingCard object with given name
	 * @param name: given name;
	 * @param number: given card number
	 * @param pin: given card pin
	 */
	public CallingCard(String name, String number, String pin){
		super(name);
		CardNumb = number;
		PIN = pin;
	}
	
	//-----------------1.3-----------------
	/**
	 * format the card 
	 * @return the super.format()+card number + pin
	 */
	@Override
	public String format(){
		String result = super.format() ;
		result += "card number = " + CardNumb + 
		    ", pin = " + PIN ;
		return result;
	}
	
	//-----------------1.11-----------------
	@Override
	public String toString(){
		return super.toString() + "[number="+CardNumb+", pin="+PIN+"]";
	}
	
	//-----------------1.12-----------------
	@Override
	public boolean equals(Object other){
		if (!super.equals(other)) {
			return false ;
		}
		CallingCard card = (CallingCard) other ;
		return (CardNumb.equals(card.CardNumb) && PIN.equals(card.PIN));
	}
	
}
