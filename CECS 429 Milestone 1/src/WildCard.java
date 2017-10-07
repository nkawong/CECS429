import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WildCard {
	private String word;

	public WildCard() {
		word = "";
	}

	public WildCard(String wild_card) {
		word = "$" + wild_card + "$";
	}

	public List<String> word_split(HashMap<String, HashSet<String>> vocab) {
		String[] substring = word.split("\\*");
		KGramIndex split_string = new KGramIndex(vocab);
		List<String> user_gram = new ArrayList<>();

		// System.out.println("This is the key(s)" + vocab.keySet());
		// System.out.println("This is the value(s)"+ value);
		for (int i = 0; i < substring.length; i++) {
			//this list has all kgrams
			user_gram.addAll(split_string.user_input_gram(substring[i]));
		}
		
		return split_string.intersect(user_gram);
	}

}
