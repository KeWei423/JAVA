
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * Ke Wei
 * Project6 
 * 
 * Description:
 * 1. Storing data in a HashMap where the key = String which is a word, and the value= List<String> the list contains non-repetitive
 * 	  words that is a word following the key. 
 * 2. I break down make essay into this hierarchy: essay<--paragraph<--sentence<--word;
 * 
 * Sentence: The Starting word of a sentence is almost the same as the following word of "." "!" "?" Therefore, I start a word by calling the 
 * 		map.get(".").get(numb) the numb is randomly generated from [0, map.get(".).length)
 * 		I repeat the above method to generate the next word and append this word to sentence until the next word is one of the end-of-sentence punctuation. 
 * Paragraph: Randomize a number [5-15] to determing how many sentences there are in a paragraph and then call the makeSentence function that manys time
 * Essay: depending on the input in main, call makeParagraph method that many tims. 
 *
 */

public class Project6 {
	private Map<String, List<String>> _wordMap = new HashMap<>();

	/**
	 * Read the file and populate the data structures
	 * 
	 * @param filename
	 * @throws FileNotFoundException 
	 */
	public Project6(String filename) throws FileNotFoundException {
		String current, next = null;
		File input = new File(filename);
		Scanner read = new Scanner(input).useDelimiter("\\s+|(?=\\p{Punct})|(?<=\\p{Punct})");
		current = read.next();
		while(read.hasNext()){
			next = read.next();
			if(!_wordMap.containsKey(current)){
				List<String> nextWord = new ArrayList<>();
				nextWord.add(next);
				_wordMap.put(current, nextWord);
			}			
			else{
				if(!_wordMap.get(current).contains(next)){
					_wordMap.get(current).add(next); 
				}
			}
			current = next;
		}
		read.close();
	}

	
	/**
	 * Make an essay with the given number of paragraphs.
	 * 
	 * @param numParagraphs
	 * @return
	 */
	public StringBuilder makeEssay(int numParagraphs) {
		StringBuilder essay = new StringBuilder();
		for(int i=0; i<numParagraphs; i++){
			essay.append(makeParagraph());
			essay.append("\n");
		}
		return essay;
	}

	
	/**
	 * make a paragraph by calling the makeSentece method n times
	 * @return para: StringBuilder 
	 */
	private StringBuilder makeParagraph(){
		StringBuilder para = new StringBuilder("    ");
		
		//generate the number of sentence in a paragraph
		int numbSentence = randNumbGenerator(5, 10);
		
		for(int i=0; i< numbSentence; i++){
			para.append(makeSentence());
		}
		
		para.append("\n");
		return para;
	}
	
	
	/**
	 * Make a sentence by calling using the data stored in map
	 * @return
	 */
	private StringBuilder makeSentence(){
		StringBuilder sentence = new StringBuilder();

		//get the first word in a sentence
		String word = _wordMap.get(".").get(randNumbGenerator(0, _wordMap.get(".").size()));
		
		while(!(word.charAt(word.length()-1) == '.') ||(word.charAt(word.length()-1) == '!')||(word.charAt(word.length()-1) == '?')){
			//format:
			if(word.equals(",") || word.equals(";")){
				// append then add space
				sentence.deleteCharAt(sentence.length()-1);
				sentence.append(word +" ");
			}
			else if(word.equals("\"")){
				//no space needs to add 
				sentence.append(word);
			}
			else if(word.equals("'") ||word.equals("!") ||word.equals(":")||word.equals("?") ){
				//replace the space with the punctuation
				sentence.deleteCharAt(sentence.length()-1);
				sentence.append(word);
			}
			else if(word.equals("-")){
				//add a new daish
				sentence.deleteCharAt(sentence.length()-1);
				sentence.append(word+word);
			}
			else{
				sentence.append(word+" ");
			}
			//generate the the position of where the new word will be
			int numb = randNumbGenerator(0, _wordMap.get(word).size());
			
			//get the new word
			word = _wordMap.get(word).get(numb);
		}
		//delete the extra space 
		sentence.deleteCharAt(sentence.length()-1);
		//add new word
		sentence.append(word+" ");
		return sentence;
	}

	
	
	/**
	 * Generate Random a number between [low, high)
	 * @param low: lower bound
	 * @param high: upper bound
	 * @return: a number in this boundary
	 */
	private int randNumbGenerator(int low, int high){
		int numb = (int) ((Math.random()*(high-low)+low));
		return numb;
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		long start = System.currentTimeMillis();
		Project6 essayMaker = new Project6("LesMes");
		System.out.println(essayMaker.makeEssay(5));
		long end = System.currentTimeMillis();
		System.out.println("Run time: "+(end -start));
	}

}
