package milestone1v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class kGramIndex {
	private HashMap<String, HashSet<String>> vocab;
	private String word;

	public kGramIndex() {
		word = "";
		vocab = new HashMap<String, HashSet<String>>();
		
	}

	public kGramIndex(String build) {
		vocab = new HashMap<String, HashSet<String>>();
	}
	public kGramIndex(HashMap<String, HashSet<String>> vocab_index){
		 word = "";
		 vocab = vocab_index;
	}
	
	public HashMap<String, HashSet<String>> getVocab() {
		return vocab;
	}
	
	public void addKey(List<String> add, String next) {
		for (String key : add) {
			if (!vocab.containsKey(key)) {
				HashSet<String> word = new HashSet<String>();
				word.add(next);
				vocab.put(key, word);
			} else {
				HashSet<String> word = vocab.get(key);
				word.add(next);
				vocab.put(key, word);
			}
			
		}
		//System.out.println(vocab.keySet());
		
	}
	public String word_builder(String term){
		word = "$"+term+"$";
		return word;
	}

	public List<String> k_gram_token(int k) {
		List<String> the_gram = new ArrayList<String>();
		String one_gram_word = "";
		if (k == 1) {
			StringBuilder removeDollar = new StringBuilder(word);
			removeDollar.deleteCharAt(word.length() - 1);
			removeDollar.deleteCharAt(0);
			one_gram_word = removeDollar.toString();
			//System.out.println(word.toString() + "asdfghj");
			for (int i = 0; i <= word.length(); i++) {
				if (i + k > one_gram_word.length()) {
					break;
				}
				/*
				 * if(k==1){ System.out.println("adfa");
				 * the_gram.add(one_gram_word.substring(i,i+k)); }
				 */

				the_gram.add(one_gram_word.substring(i, i + k));

			}
		} else {
			for (int i = 0; i <= word.length(); i++) {
				if (i + k > word.length()) {
					break;
				}
				/*
				 * if(k==1){ System.out.println("adfa");
				 * the_gram.add(one_gram_word.substring(i,i+k)); }
				 */

				the_gram.add(word.substring(i, i + k));

			}
		}
		return the_gram;
	}

	public List<String> user_input_gram(String builder) {
		System.out.println("this is the word: " + builder + " ");
		List<String> grams = new ArrayList<String>();
		// List<String> return_words = new ArrayList<String>();
		for (int i = 0; i < builder.length(); i++) {
			if (builder.length() > 3) {
				if (i + 3 > builder.length()) {
					break;
				}
				grams.add(builder.substring(i, i + 3));
				// System.out.println(grams.get(i));
			} else {
				/*
				 * if(i+2 > builder.length()){ break; }
				 */
				grams.add(builder.substring(i, i + builder.length()));
				// System.out.println(grams.get(i));
				break;
			}
		}
		return grams;
	}

	public List<String> intersect(List<String> grams) {
		List<List<String>> words = new ArrayList<>();
		List<String> copy = new ArrayList<>();
		
		List<String> list= new ArrayList<>();
		System.out.println("it gets here" + grams);
		for(String key:grams){
			List<String> temp;
			if(vocab.containsKey(key)){
				temp = new ArrayList<String>(vocab.get(key));
				if(list.isEmpty()){
					list = temp;
				}
				else{
					list.retainAll(temp);
				}
			}
		}
		for(String key:grams){
			if(vocab.containsKey(key)){
				List temp = new ArrayList<String>(vocab.get(key));
				System.out.println("These are the keys: " + temp);
			}
		}
		System.out.println(list);

		
		return list;

	}

}
