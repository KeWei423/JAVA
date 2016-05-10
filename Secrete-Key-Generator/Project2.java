import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;


/**
 * Student: Ke Wei
 * @PCC_ID 10126458
 *
 * Short description of the program here:
 * Part1: use the alphabet to generate a encryption key
 * Part2: use part1's encryption key to generate a decryption key
 * Part3: input/output file
 * 		  a. read a "secrete.txt" file and find the how many times each letter's appeared
 *        b. use the counts of each letter find out the relative frequency of each letter's appearance
 *        c. Print a relative frequency chart
 * 
 */

public class Project2 {
	
	/*--------------------------------------Part1--------------------------------------*/
	/**
	 * get lower case alphabets in an array in order
	 * @return char array[] of alphabets 
	 */
	public static char[] getAllLetters(){
		String letters = "abcdefghijklmnopqrstuvwxyz";
		char alphabet [] = new char [letters.length()];
		for(int i=0; i<letters.length(); i++)
		{
			alphabet[i] = letters.charAt(i);
		}
		return alphabet;
	}
	
	/**
	 * generate a number between i - 26
	 * @param min: the lower boundary 
	 * @return a number between i and 26
	 */
	public static int getSwapPosition(int min){
		final int MAX = 26;
		int random = min+(int)(Math.random()*(MAX - min));
		return random;
	}
	
	/**
	 * Switch the elements of two given location in a given array
	 * @param i: position 1
	 * @param j: position 2
	 * @param alphabet: the char array given 
	 */
	public static void swapPosition(int i, int j, char [] alphabet)
	{
		char temp= alphabet[i];
		alphabet[i] = alphabet[j];
		alphabet[j] = temp;
	}
	
	/**
	 * shuffle elements in the array randomly
	 * @param alphabet: the given array
	 */
	public static void shuffleRandomly(char [] alphabet) {
		for(int i=0; i<alphabet.length; i++)
		{
			//get j
			int j = getSwapPosition(i);
			swapPosition(i, j, alphabet);
		}
	}
	
	/**
	 * generate an Encryption key
	 * @return: shuffled alphabet
	 */
	public static String generateEncryptionKey(){
		 char [] original = getAllLetters();
		 shuffleRandomly(original);
		 String EncryptKey = String.valueOf(original);
		 return EncryptKey;
	}
	
	
	/*--------------------------------------Part2--------------------------------------*/
	/**
	 * generate a decryptionKey by shuffling EncryptionKey
	 * @param encryptionKey
	 * @return: shuffled encryptionKey
	 */
	public static String getDecryptionKey(String encryptionKey){
		char [] encrypt =   encryptionKey.toCharArray();
		shuffleRandomly(encrypt);
		String decryptKey = String.valueOf(encrypt);
		return decryptKey;
	}
	
	
	/*--------------------------------------Part3--------------------------------------*/
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static int [] getLetterCounts(String fileName) throws FileNotFoundException{
		File inputFile = new File(fileName);
		Scanner in = new Scanner (inputFile);
		int [] letterOccurence = new int [26];
		while(in.hasNextLine()){
			String sentence = in.nextLine();
			for(int i=0; i<sentence.length(); i++){
				char letter = sentence.charAt(i);
				if(isLetter(letter)){
					letter = upper2lower(letter);
					int pos= letter -'a';
					letterOccurence[pos] +=1;
				}
				
			}
		}
		in.close();
		return letterOccurence;
	}
	
	 /**
     * Check if a given character is a lower case letter
     *
     * @param c The character
     * @return true if lower case letter, false otherwise
     */
    public static boolean isLowerCaseLetter(char c)
    {
        return c >= 'a' && c <= 'z';
    }


    /**
     * Check if a given character is an upper case letter
     *
     * @param c The character
     * @return true if upper case letter, false otherwise
     */
    public static boolean isUpperCaseLetter(char c) {

        return c >= 'A' && c <= 'Z';
    }

    
    /**
     * Check if a given character is a letter
     *
     * @param c The character
     * @return true if a letter, false otherwise
     */
    public static boolean isLetter(char c) {
        boolean isLower = isLowerCaseLetter(c);
        boolean isUpper = isUpperCaseLetter(c);
        return (isLower == true || isUpper == true);
    }
    
    /**
     * Convert an upper case letter to lower case
     *
     * @param c The upper case character
     * @return The corresponding lower case letter
     */
    public static char toLowerCase(char c) {
        return (char) (c + 'a' - 'A');
    }
	
    /**
     * convert upper case to lower case 
     * @param c
     * @return lower case c
     */
	public static char upper2lower(char c) {
		if(isUpperCaseLetter(c)){
			c = toLowerCase(c);
		}
		return c;
	}
	
	/**
	 * find how often each letter is used: count / total
	 * @param letterCount
	 * @return percentage of each letter in an array
	 */
	public static double [] getRelativeFrequencies(int [] letterCount){
		int total = totalFreq(letterCount);
		double [] letterFreq = new double [26];
		DecimalFormat df = new DecimalFormat("#.##");
		for(int i=0; i<letterFreq.length; i++){
			letterFreq[i]= Double.valueOf(df.format((letterCount[i] / (double)total * 100)));
		}
		return letterFreq;
	}
	
	/**
	 * find the total words appeared in this txt file
	 * @param letterCount
	 * @return total words counted
	 */
	public static int totalFreq(int [] letterCount){
		int sum=0;
		for(int n: letterCount){
			sum +=n;
		}
		return sum;
	}
	

	/**
	 * print letter used frequency chart 
	 * @param letterFrequence: double array[] that contains each letter's relative frequency
	 * @param output: the output file 
	 * @throws FileNotFoundException
	 */
	public static void printFrequencyChart (double[] letterFrequence, PrintWriter output) throws FileNotFoundException{
		int max = findMax(letterFrequence);
		for(int i=max ; i>=1 ; i--){
			if(i<10){
				output.print(" ");
			}
			output.print((int)i + " ");
			
			// print out * follow the rule from giving
			for(int j = 0; j<26 ; j++){
				if(i<=letterFrequence[j]){
					output.print("* ");
				}
				else{
					output.print("  ");
				}
			}
			output.println();
		}
		char [] alph = getAllLetters();
		PrintChar(alph, output);
	}
	
	
	public static int findMax(double [] array) {
		int max =(int) array[0];
		for(double n: array){
			if (n>max){
				max = (int)n;
			}
		}
		return max;
	}
	
	
	public static void PrintChar(char [] arr, PrintWriter output) {
		output.print("  ");
		for(char c: arr) {
			output.print(" "+c);
		}
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		//print frequency chart
		PrintWriter out= new PrintWriter("project2Output.txt");
		
		/*Par1: generate EncrptionKey*/ 
		String encrypKey = generateEncryptionKey();
		out.println("EncryptionKey: "+encrypKey);
		
		
		/*Par2: generate DecrptionKey*/ 
		String decrypKey = getDecryptionKey(encrypKey);
		out.println("DecryptionKey: "+decrypKey);
		out.println();
		
		/*Par3: get "secrete.txt"*/
		 	//get letter counts
		String fileName = "secret.txt";
		int [] letterOccurrence = getLetterCounts(fileName);
		out.println("letter occurrence: "+ Arrays.toString(letterOccurrence));
		out.println();
			//get letter frequency
		double [] letterFrequence = getRelativeFrequencies(letterOccurrence);
		out.println("letter frequency: "+ Arrays.toString(letterFrequence));
		out.println();
			//output file
		out.println("Frequency Chart");
		printFrequencyChart(letterFrequence, out);
		out.close();
	}
	
}
