package milestone1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PositionalPosting {
	private Postings posts;
	private HashMap<String,List<Postings>> aIndex;
	
	public PositionalPosting(){
		posts = new Postings();
		aIndex = new HashMap<String,List<Postings>>();
	}
	public void addTerm(String term,int doc_id,int position){
		List<Postings> term_map;
		if(!aIndex.containsKey(term)){
			/* if the list is does not contain term, add it */
			term_map = new ArrayList<Postings>();
			posts = new Postings(doc_id, position);
			aIndex.put(term, term_map);
		}
		else{
			// the term is in aIndex, then check if the doc_id is in postings and then check if the that position already exists
			term_map = aIndex.get(term);
			if(term_map.contains(doc_id)){
				if(aIndex.get(aIndex.get(term)).size()-1 < (int) posts.getPosition()){
					posts = new Postings(doc_id,position);
					term_map.add(posts);
					aIndex.put(term, term_map);
				}
			}
			/*
			List<Postings> term_map = new ArrayList<Postings>();
			if(aIndex.get(aIndex.get(term)).size()-1 < (int) posts.getPosition()){
				// Checks to see if only the position is not there 
				posts = new Postings(doc_id, position);
				term_map.add(posts);
				aIndex.put(term, term_map);
			}
			else if(!aIndex.get(term).contains(posts.getDocID())){
				
			}*/
		}
	}
	public List<Postings> getPostings(String term){
		if(aIndex.containsKey(term)){
			return aIndex.get(term);
		}
		return null;
	}
	public int getTermCount(){
		return aIndex.size();
	}
	public String[] getDictionary(){
		String[] dictionary = new String[aIndex.size()];
		
 		return dictionary;
	}
	
	
}
