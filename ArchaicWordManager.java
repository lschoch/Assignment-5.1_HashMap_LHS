import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;

/** 
 * A driver class to read the contents of a CSV file into a 
 * dictionary of archaic words, print the dictionary and then
 * find an print 5 words with definitions and hashKeys.
 * 
 * @author Lawrence Schoch
 * @version 1.1
 */
public class ArchaicWordManager {

	public static void main(String[] args) {
		ArchaicWordDictionary myArchWordDict = new ArchaicWordDictionary();
		// Read the source file and add word-value pairs to the dictionary
		String fileName = "ArchaicWordValuePairs.txt";
		String line = "";
		String word = "";
		String nextWord = "";
		String def = "";
		try {
			Scanner data = new Scanner(new File(fileName), "UTF-8");
			while (data.hasNextLine()) {
				line = data.nextLine();
				Scanner lineProcessor = new Scanner(line);
				lineProcessor.useDelimiter("\t");
				while (lineProcessor.hasNext()) {
					word = lineProcessor.next();
					def = lineProcessor.next();
				}
//				lineProcessor.close();
				myArchWordDict.addDef(word, def);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e);
		}
		
		// Display the dictionary's contents with the toString method
		System.out.println(myArchWordDict.toString());
		
		// Create key iterator
		Set<String> archKeySet = myArchWordDict.getKeySet();
		Iterator<String> keyIt = archKeySet.iterator();
		
		/* Iterate through the keySet and select every 87th (436/5)
		 * to be printed.
		 */
		int counter = 1;
		while (keyIt.hasNext()) {
			nextWord = keyIt.next();
			if (counter % 15 == 0) {
				System.out.println("word: " + nextWord + " --> definition: "
						+ myArchWordDict.getDefinition(nextWord));
				System.out.println("hash from hashCode = " + nextWord.hashCode());
				System.out.println("manually calculated hash using Horner method = "
						+ myArchWordDict.hash(nextWord));
				System.out.println("hashIndex = " 
						+ myArchWordDict.getHashIndex(nextWord));
				System.out.println();
			}
			counter ++;
		}	
	}// end main

}// end class
