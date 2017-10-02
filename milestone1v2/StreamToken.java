package milestone1v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import org.tartarus.snowball.ext.englishStemmer;

public class StreamToken implements TokenStream {
	private Scanner reader;
	private List<String> word;
	private HashMap<String, ArrayList<Integer>> term_position;
	private int counter;

	public StreamToken(File fileToOpen) throws FileNotFoundException {
		reader = new Scanner(new FileReader(fileToOpen));
	}

	public StreamToken(String text) {
		reader = new Scanner(text);
	}

//	public List<String> list_word(List<String> terms) {
//		word = new ArrayList<String>();
//		
//		return word;
//	}

	@Override
	public String nextToken() {
		if (!hasNextToken())
	         return null;

	      String next = reader.next().replaceAll("\\W", "").toLowerCase();
	     
	      
	      //System.out.println(next);
	      return next.length() > 0 ? next : 
	       hasNextToken() ? nextToken() : null;
	}

	@Override
	public boolean hasNextToken() {
		// TODO Auto-generated method stub
		return reader.hasNext();
	}

}