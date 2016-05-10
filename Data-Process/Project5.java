import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Project 5
 * @author Ke Wei
 * ID: 10126458
 * 
 */

public class Project5 {
	
	/**
	 * process to read the 2 different files 
	 * @param fileName
	 * @return arraylist of the processed file 
	 */
	public static ArrayList<GroupInfo> readFile(String fileName) {
		ArrayList<GroupInfo> temp = null;
		if(fileName.equals("pcc_students.txt")){
			temp = readPCC(fileName);
		}
		else{
			temp = readUC(fileName);
		}
		return temp;
	}
	
	/**
	 * read the uc_students.txt
	 * @param fileName: uc_students.txt
	 * @return uc students info in an arraylist
	 */
	public static ArrayList<GroupInfo> readUC(String fileName){
		ArrayList<GroupInfo> list = new ArrayList<>();
		File uc = new File(fileName);
		try (Scanner input = new Scanner(uc)){
			input.useDelimiter(",|\\n");
			while(input.hasNext()){
				String school = input.next();
				String id = input.next();
				String name = input.next();
				GroupInfo hold = new GroupInfo(name, school, id);
				list.add(hold);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * read the pcc students file 
	 * @param fileName: "pcc_students.txt"
	 * @return: pcc students info in an arraylist
	 */
	public static ArrayList<GroupInfo> readPCC(String fileName){
		ArrayList<GroupInfo> list = new ArrayList<>();
		File pcc = new File(fileName);
		try (Scanner input = new Scanner(pcc)){
			input.useDelimiter(",|\\n");
			while(input.hasNext()){
				String name = input.next();
				String id = input.next();
				GroupInfo hold = new GroupInfo(name, "PCC", id);
				list.add(hold);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * find the id number that's common in both files by using ID
	 * @param uc: list
	 * @param pcc: list
	 * @return list that contains the unique student info found by searching for id 
	 */
	public static List<GroupInfo> findCommon(List<GroupInfo> uc, List<GroupInfo> pcc){
		List<GroupInfo> result = new ArrayList<>();
		for(int i= 0 ; i<pcc.size(); i ++){
			int pccID = pcc.get(i).getID();
			int pos = Search(uc, pccID);
			if(pos != -1){
				result.add(uc.get(pos));
			}
		}
		return result;
		
	}
	

	/**
	 * find the position in an arraylist that has this ID number 
	 * @param uc: file
	 * @param pccID: int
	 * @return position int
	 */
	public static int Search(List<GroupInfo> uc, int pccID){
		int pos = Search(uc, 0, uc.size(), pccID);
		return pos;
	}
	
	
	/**
	 * searching for the position in an arraylist that has this ID number giving the start and end position
	 * @param uc: file
	 * @param start: int
	 * @param end: int
	 * @param ID: int
	 * @return pos: int
	 */
	public static int Search(List<GroupInfo> uc, int start, int end, int ID){
		int pos = -1;
		if(start < end){
			int mid = (start + end)/2;
			if(ID == (uc.get(mid).getID())){
				pos = mid;
			}
			else if(ID < (uc.get(mid).getID())){
				pos = Search(uc, start, mid, ID);
			}
			else if(ID > (uc.get(mid).getID())){
				pos = Search(uc, mid+1, end, ID);
			}
		}
		return pos;
		
	}
	
	
	/**
	 * write the result out as a file 
	 * @param list: List
	 * @param outs: PrintWriter
	 */
	public static void FileResult(List<GroupInfo> list, PrintWriter outs){
		for(int i=0; i<list.size(); i++){
			outs.print(list.get(i).getName()+","+list.get(i).getID()+","+list.get(i).getSchool());
			outs.println();
		}
		outs.close();
	}
	
	
	
	public static void main(String[] args) throws IOException{
		PrintWriter out = new PrintWriter("result.txt");
		List<GroupInfo> uc = readFile("uc_students.txt");
		Collections.sort(uc); //n
		List<GroupInfo> pcc = readFile("pcc_students.txt"); 
		List<GroupInfo> list = findCommon(uc, pcc);
		FileResult(list, out);
	}

}
