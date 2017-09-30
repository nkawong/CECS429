package milestone1v2;

import java.util.ArrayList;

public class Postings {
	private int docID;
	private ArrayList<Integer> position;
	
	public Postings(){
		docID = 0;
		position = null;
	}
	public Postings(int id, ArrayList<Integer> position){
		this.docID = id;
		this.position= position;
	}
	public Postings(ArrayList<Integer> pos){
		position = pos;
	}
	public int getDocID() {
		return docID;
	}
	public ArrayList<Integer> getPosition() {
		return position;
	}
	public void addPosition(int pos){
		position.add(pos);
	}
	public int getListSize(){
		return position.size();
	}
	@Override
	public String toString(){
		
		return ""+ docID;
	}
	
}