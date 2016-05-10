/**
 * DriverLicense object is a general Card but with an expiration year
 * @author Ke
 *
 */

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DriverLicense extends Card{
	//-----------------1.1-----------------
	private int ExpireYear;
	
	/**
	 * default ctor
	 */
	public DriverLicense(){
		super();
		ExpireYear = 0;
	}
	
	//-----------------1.2-----------------
	/**
	 * ctor of Driver License with given name
	 * @param name: given name
	 * @param year: given year
	 */
	public DriverLicense (String name, int year){
		super(name);
		ExpireYear = year;
	}
	
	//-----------------1.3-----------------
	@Override
	public String format(){
		String result = super.format() ;
		result += "\nDriver License Expire Year: " + ExpireYear;
		return result;
	}
	
	//-----------------1.7-----------------

	@Override
	public boolean isExpired(){
		Calendar calendar = new GregorianCalendar();
		calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		return ExpireYear < currentYear;
	}
	
	//-----------------1.11-----------------
	@Override
	public String toString(){
		return super.toString()+"[Expiration year="+ExpireYear+"]";
	}
	
	//-----------------1.12-----------------
	@Override

	public boolean equals(Object other){
		if (!super.equals(other)) {
			return false ;
		}
		DriverLicense card = (DriverLicense) other ;
		return Integer.toString(ExpireYear).equals(card.ExpireYear) ;
	}
	
}
