/**
 * 
 * @author YOUR NAME HERE
 * @PCC_ID YOUR PCC ID HERE
 * 
 * CS 3B Programming Project 1
 * 
 * Short description of the program here:
 * 
 * 
 * 
 */

import java.util.Scanner;

public class Project1 {
	public static void main(String[] args) {
//		TODO
			// get input from the user and encode/decode until user quits
		
	}

	/**
	 * Check if a given character is a lower case letter
	 *  
	 * @param c The character
	 * @return true if lower case letter, false otherwise
	 */
	public static boolean isLowerCaseLetter(char c) {
		return c >= 'a' && c <= 'z';
	}

	/**
	 * Check if a given character is an upper case letter
	 *  
	 * @param c The character
	 * @return true if upper case letter, false otherwise
	 */
	public static boolean isUpperCaseLetter(char c) {
//		TODO
		return true;
	}

	/**
	 * Check if a given character is a letter
	 *  
	 * @param c The character
	 * @return true if a letter, false otherwise
	 */
	public static boolean isLetter(char c) {
//		TODO
		return true;
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
	 * Encode or decode a character according to the fixed substitution pattern.
	 * 
	 * @param c The character to encode
	 * @param encode true for encoding, false for decoding
	 * @return The encoded character
	 */
	public static char codeChar(char c, boolean encode) {
//		TODO
		return 'a';
	}
	
//	TODO
	
}