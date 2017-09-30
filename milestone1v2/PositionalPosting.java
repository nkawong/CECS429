package milestone1v2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.tartarus.snowball.ext.englishStemmer;

public class PositionalPosting {
	private Postings posts;
	private HashMap<String, List<Postings>> aIndex;
	private int pos;
	private List<String> vocab = new ArrayList<String>();

	public PositionalPosting() {
		posts = new Postings();
		aIndex = new HashMap<String, List<Postings>>();
		pos=1;
	}

	public HashMap<String, List<Postings>> getMap(){
		return aIndex;
	}
	public void setPos(int pos){
		pos = pos;
	}
	public void addTerm(String term, int id, int position) {
		List<Postings> posting_list;
		if (!aIndex.containsKey(term)) {
			/* if the list is does not contain term, add it */
			posting_list = new ArrayList<Postings>();
			//aIndex.put(term, new ArrayList<Postings>());
			posts = new Postings(id, new ArrayList<Integer>());
			//posts.getPosition().clear();
			posts.addPosition(position);
			//ystem.out.println(term + " " + posts.getPosition());
			//aIndex.get(term).add(posts);
			//System.out.println(posts.getDocID());
			posting_list.add(posts);
			aIndex.put(term, posting_list);
			
			//aIndex.get(term).add(posts);
			
		} else {
			// the term is in aIndex, then check if the doc_id is in postings
			// and then check if the that position already exists
			posting_list = aIndex.get(term);
			if ((posting_list.get(posting_list.size() - 1)).getDocID() == id) {
				// posts = new Postings(position);
				// posts.getPosition().clear();

				posting_list.get(posting_list.size() -1).addPosition(position);
				// System.out.println(posting_list.size());
				System.out.println(term + " " + posting_list.get(posting_list.size() -1).getDocID());

			}
			else{
				posts = new Postings(id, new ArrayList<Integer>());
				posts.addPosition(position);
				posting_list.add(posts);
			}
				//System.out.println(term + " " + posts.getPosition());
				aIndex.put(term, posting_list);
				//posting_list.get(posting_list.size() - 1).addPosition(i);
				//System.out.println(posting_list.get(posting_list.size() - 1).getPosition());
				//posts.getPosition().add(i);
				//posting_list.addPosition(position);
				//posting_list.add(posts);
				//aIndex.put(term, posting_list);
				
			}
			/*else{

				aIndex.put(term, new ArrayList<Postings>());
				//posting_list = new ArrayList<Postings>();
				posts = new Postings(id, position);
				posts.addPosition(i);
				System.out.println(posts.getDocID());
				aIndex.get(term).add(posts);
				//posting_list.add(posts);
				//aIndex.put(term, posting_list);
				i++;
			}*/
		}
	
		//System.out.println(aIndex.size());
		/*
		 * List<Postings> term_map = new ArrayList<Postings>();
		 * if(aIndex.get(aIndex.get(term)).size()-1 < (int)
		 * posts.getPosition()){ // Checks to see if only the position is not
		 * there posts = new Postings(doc_id, position); term_map.add(posts);
		 * aIndex.put(term, term_map); } else
		 * if(!aIndex.get(term).contains(posts.getDocID())){
		 * 
		 * }
		 */
	

	public List<Postings> getPostings(String term) {
		if (aIndex.containsKey(term)) {
			return aIndex.get(term);
		}
		return null;
	}

	public int getTermCount() {
		return aIndex.size();
	}

	public String[] getDictionary() {
		int i = 0;
		String[] dictionary = new String[getTermCount()];
		for(String dict : aIndex.keySet()){
			dictionary[i] = dict;
			i++;
		}

		Arrays.sort(dictionary);
		return dictionary;
	}

	
}
