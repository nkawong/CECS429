package milestone1v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class kGramIndex {
	private HashMap<String, HashSet<String>> vocab;
	private String word;

	public kGramIndex() {
		word = "";
	}
	
	public kGramIndex(String build) {
		word = "$" + build + "$";
	}
	
	public void addKey(List<String> add){
		
	}
	public List<String> k_gram_token(int k) {
		List<String> the_gram = new ArrayList<String>();
		for (int i = 0; i <= word.length(); i++) {
			if (i + k > word.length()) {
				break;
			}
			the_gram.add(word.substring(i, i + k));
		}

		return the_gram;
	}

}
