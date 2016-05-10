
public class IDCard extends Card{
	//-----------------1.1-----------------
	private String IDNumber;
	
	/**
	 * default ctor
	 */
	public IDCard(){
		super();
		IDNumber = "";
	}
	//-----------------1.2-----------------
	/**
	 * ctor with given name 
	 * @param name: given name
	 * @param id: given id number
	 */
	public IDCard(String name, String id){
		super(name);
		IDNumber = id;
	}
	
	//-----------------1.3-----------------
	@Override
	public String format(){
		String result = super.format() ;
		result += "\nID Card Number: " + IDNumber;
		return result;
	}
	
	//-----------------1.11-----------------
	@Override
	public String toString(){
		return super.toString()+"[ID Numbe ="+IDNumber+"]";
	}
	
	//-----------------1.12-----------------
	@Override
	public boolean equals(Object other){
		if (!super.equals(other)) {
			return false ;
		}
		IDCard card = (IDCard) other ;
		return IDNumber.equals(card.IDNumber) ;
	}
}
