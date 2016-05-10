
public class GroupInfo implements Comparable<GroupInfo> {
	private String _name;
	private String _school;
	private int _ID;
	
	public GroupInfo(String name, String school, String ID){
		_name = name;
		_school = school;
		_ID = Integer.parseInt(ID);
	}
	
	
	public void Print(){
		System.out.println("name: "+_name+" ID: "+ _ID+" school: "+ _school);
		
	}
	
	
	public String getName(){
		return _name;
	}
	
	
	public int getID(){
		return _ID;
	}
	
	
	
	public String getSchool(){
		return _school;
	}
	
	
	public void setName(String name){
		_name = name;
	}
	
	
	public void setID(String id){
		_ID = Integer.parseInt(id);
	}
	
	
	
	public void setSchool(String school){
		_school = school;
	}


	@Override
	public int compareTo(GroupInfo o) {
		int OtherID = o.getID();
		if(this.getID() > OtherID){
			return 1;
		}
		
		else if(this.getID() < OtherID){
			return -1;
		}
		return 0;
	}
	
}
