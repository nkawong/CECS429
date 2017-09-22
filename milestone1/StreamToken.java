package milestone1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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

	public HashMap<String, ArrayList<Integer>> position_term(List<String> terms) {
		
		term_position = new HashMap<String, ArrayList<Integer>>();
		List<Integer> word_position = new ArrayList<Integer>();
		//counter = 0;
		for(int i = 0; i<terms.size();i++){
			if(term_position.containsKey(terms.get(i))== false){
				word_position.add(i + 1);
				//System.out.println(word_position + " " + i);
				term_position.put(terms.get(i), new ArrayList<Integer>());
				term_position.get(terms.get(i)).add(i+1);
				//System.out.println(word_position);
				//List<String> key = (List<String>) term_position.keySet();
				//System.out.println(term_position.keySet() + " " + term_position.get(terms.get(i)));
				word_position.clear();
				
			}
			else{
				term_position.get(terms.get(i)).add(i+1);
				//System.out.println(word_position);
				
			}
		}
		for(String key : term_position.keySet()){
			Object value = term_position.get(terms.iterator().next());
			System.out.println(key + " " + term_position.get(key));
		}
//		for (String words : word) {
//			if (!term_position.containsKey(words)) {
//				word_position.add(counter);
//				term_position.put(words, word_position);
//				counter++;
//			}
//			else{
//				word_position = term_position.get(words);
//				if(word_position.get(word_position.size()-1) < counter){
//					word_position.add(counter);
//					term_position.put(words,word_position);
//					counter++;
//				}
//			}
//		}
		return term_position;
	}

	@Override
	public String nextToken() {
		if (!hasNextToken())
	         return null;
	      
	      String next = reader.next().replaceAll("\\W", "").toLowerCase();
	      return next.length() > 0 ? next : 
	       hasNextToken() ? nextToken() : null;
	}

	@Override
	public boolean hasNextToken() {
		// TODO Auto-generated method stub
		return reader.hasNext();
	}

}
